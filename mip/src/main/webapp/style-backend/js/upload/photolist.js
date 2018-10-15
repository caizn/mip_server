var outFile = new Array();
function selectAll(){
	var checkboxs=$("[name = check]:checkbox");
	for (var index = 0; index < checkboxs.length; index++) {
		if ($("#batch").is(":checked")) {
			checkboxs[index].checked=true;
		}else{
			checkboxs[index].checked=false;
		}
	}
	
}
$(function() {
    $.ajaxSetup({
        async: false
    });
    imgSelectorInit();
    ipostSortInit();
    loadphoto();
    //$('#file_upload-button').attr("style","border-style: solid;border-color: red;");
    //var outFile=new Array();
    var swf = $('#swf').val();

    $('#file_upload').uploadify({
        'swf': swf,
        'uploader': 'uploadify.action',
        'fileTypeDesc': 'Image Files',
        'buttonClass': 'btn pl_add btn-primary',
        'buttonText': '<i class="fa fa-plus"></i> 添加图片',
        'buttonCursor': 'pointer',
        'height': 20,
        'width': 100,
        'fileTypeExts': '*.jpeg; *.gif; *.jpg; *.png',
        'fileSizeLimit': '300KB',
        'queueSizeLimit': '50',
        'overrideEvents': ['onSelectError', 'onDialogClose'],
        // Put your options here
//        'onInit': function(instance) {
//            if ((10 - $("li.file").length) <= 0) {
//            	$('#file_upload').uploadify('disable', true);
//            	$('#file_upload').uploadify('settings','buttonText','上传已达限制...');
//            }
//        },
        'onSelectError': function(file, errorCode, errorMsg) { //返回一个错误，选择文件的时候触发  
            switch (errorCode) {
            case - 100 : alert("上传的文件数量已经超出系统限制的" + $('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！");
                break;
            case - 110 :
//                alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！");
                outFile.push("文件 [" + file.name + "] 大小超出系统限制的" + $('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！<br>");
                break;
            case - 120 : alert("文件 [" + file.name + "] 大小异常！");
                break;
            case - 130 : alert("文件 [" + file.name + "] 类型不正确！");
                break;
            }
        },
        'onUploadSuccess': function(file, data, response) {
            var dataObj = $.parseJSON(data);
            var tip = dataObj.tip;
            if (tip.result) {
                var file = tip.fileObj;
                var fileuri = xxset(file.fileurl);
                $('fileList').append(addFile(file.fileurl, fileuri, "", ""));
                uploadbtn();
                savePhoto();
            }
        },
        'onUploadStart': function(file) {
        	uploadbtn();
        },
        'onQueueComplete': function(queueData) {
            /*alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');*/
            if (outFile.length > 0) {
                var alt = "";
                for (var i in outFile) {
                    alt += outFile[i];
                }
                $.altmodal("提示", alt,
                function() {
                    outFile = new Array();
                    location.reload();
                });
            } else {
                $.sucmodal("提交", "相片保存成功！", 2000);
            }
        }
    });
    uploadbtn();
    $('#id').val();
});

function uploadbtn(){
	var queueSize=$('#file_upload').uploadify('settings','queueSizeLimit');
    if ($("#fileList li.post.file").length > 0) {
        if ($("#bsubmit").length == 0) {
            $('#file_upload-button').attr("style", "position:absolute;");
            //$('#file_upload-button').attr("style", "border-style: solid;border-color: red;");
            //$('#file_upload-button').parent().attr("style","border-style: solid;border-color: red;");
            $('#file_upload-button').parent().append('<div class="subbtn" style="width: 200px;"></div>')
        }
    }
    if($("#fileList li.post.file").length >= queueSize){
    	$('#file_upload').uploadify('disable', true);
        $('#file_upload').uploadify('settings','buttonText','上传已达限制...');
    	if ($("#bsubmit").length == 0) {
            $('#file_upload-button').attr("style", "position:absolute;");
            $('#file_upload-button').parent().append('<div class="subbtn" style="margin-left:150px;style="width: 200px;""></div>')
            }else{
        	$("#bsubmit").parent().attr("style","margin-left:150px");
        }
    }
    $('#bsubmit').on("click", btnSavePhoto);
    $('.del').on("click", delPhoto);
}

function delBatch(){
	
	var checkboxs=$("[name = check]:checkbox");
	var i=0;
	var url="[";
	for (var index = 0; index < checkboxs.length; index++) {
		if (checkboxs[index].checked==true) {
			url+="{\"url\":\""
			url+=$(checkboxs[index]).next().next().attr("href")+"\"},";
			i++;
		}
	}
	/*var imgs=$("li.post.file").children("a");
	$(imgs).each(function(i,ite){
		
	});*/
	url=url.substring(0,url.length-1);
	url+="]"
	if (i==0) {
		$.sucmodal("提示", "请至少选择一项",2000);
	} else {
		$.altmodal("删除", "您确定要批量删除图片吗?",function(){
			$.post("WxPhotoAction!batchDelPhoto.action", {"url":url,"id": $("#id").val()},function(data){
				if (data) {
					$.sucmodal("提示", "相片删除成功",2000,function(){
						location.href="albumsPhoto.jsp?id="+$("#id").val();
					});
				}else{
					$.sucmodal("提示", "删除失败！请联系系统管理员",2000);
				}
			});
		});
	}
}
function loadphoto() {
    $.post("WxPhotoAction!getPhoto.action", {
        "id": $("#id").val()
    },
    function(data) {
        var len = data.length;
        $(data).each(function(i, ite) {
            if (i != len - 1) {
                $('fileList').append(addFile(ite.url, ite.url, ite.title, ite.intro));
            } else if (!ite.sign) {
                $.altmodal("空间问题", "您的空间已满，无法上传图片");
                $("#file_upload-button").remove();
            }
        });
        /*
        if ($("#fileList li").length > 0) {
            if ($("#bsubmit").length == 0) {
                $('#file_upload-button').attr("style", "border-style: solid;border-color: red;position:absolute;");
                $('#file_upload-button').parent().attr("style","border-style: solid;border-color: red;");
                $('#file_upload-button').parent().append('<div class="subbtn"><button id="bsubmit" type="submit" data-loading-text="提交中..." class="btn">保存</button></div>')
            }
            $('#bsubmit').on("click", savePhoto);
            $('.del').on("click", delPhoto);
        }*/
    });
}

function addFile(url, zurl, title, intro) {
    var el = $('<li class="post file"><input  type="checkbox" name="check">' + '<input type="hidden" value="' + zurl + '" name="url[][]">' + '<a class="thumb" href="' + url + '" target="_blank" title="点击查看原图，拖动排序" style="background-image:url(\'' + url + '\');background-size:100px;"><span class="file-thumb-title">上传成功</span></a>' + '<dl class="form data">' + '<dt>标题</dt><dd class="title"><input type="text" class="text" placeholder="无标题请留空" name="title[][]" value="' + title + '" /></dd>' + '<dt>描述</dt><dd class="description"><textarea name="description[][]">' + intro + '</textarea></dd>' + '</dl>' + '<ul class="action">' + '<li class="delete"><a class="ir del">删除</a></li>' + '<li class="sort"><a class="ir">排序</a></li>' + '</ul>' + '</li>').appendTo($('#fileList'));
    return el;
}

function imgSelectorInit() {
    $('#imgSelectorChoice').delegate('img', 'click',
    function() {
        var el = $(this);
        if (!el.hasClass('selected')) buildSelectedImg(el);
    }).delegate('img', 'mouseenter',
    function() {
        $(this).addClass('hovered');
    }).delegate('img', 'mouseleave',
    function() {
        $(this).removeClass('hovered');
    });
}
function ipostSortInit() {
    $('.ipost-list').sortable({
        items: '> .post',
        containment: 'parent',
        appendTo: 'parent',
        tolerance: 'pointer',
        axis: 'y',
        placeholder: 'holder',
        forceHelperSize: true,
        forcePlaceholderSize: true,
        opacity: 0.8,
        cursor: 'ns-resize'
    });
}
function btnSavePhoto(){
	savePhoto();
    var outFileSize=outFile.length;
    if(outFileSize>0){
  	  var alt = "";
        for (var i in outFile) {
            alt += outFile[i];
        }
        $.altmodal("提示", alt,
        function() {
            outFile = new Array();
            location.reload();
        });
    }else{
  	  $.sucmodal("提示", "相片保存成功！",2000,function(){
  		  location.reload();
  	  });
    }
}
function savePhoto() {
    var url = "";
    var title = "";
    var intro = "";
    var content="[";
    $("#fileList li.post.file").each(function(i, ite) {
        /*if (i != 0) {
            url = url + ",";
            title = title + ",";
            intro = intro + ",";
        }*/
    	content+="{"
        url =  $(ite).children().get(1).value;
        var x = $(ite).children().get(3);
        title =$(x).find("input").val();
        intro = $(x).find("textarea").val();
        content+="\"url\":\""+url+"\",";
         content+="\"title\":\""+title+"\","
          content+="\"intro\":\""+intro+"\""
       
        content+="},"
    });
    content=content.substring(0,content.length-1);
    content+="]";
    $.post("WxPhotoAction!savePhoto.action", {
        "id": $("#id").val(),
       "context":content
    },
    function(data) {
        if (!data) {
            if (outFile.length > 0) {
                outFile = new Array();
                outFile.push("图片保存错误，请重新检查上传的图片及内容");
            } else {
                outFile.push("图片保存错误，请重新检查上传的图片及内容");
            }
        }
    })
}

function delPhoto() {
    var url = $(this).parent().parent().parent().children().get(1).value;
    var xx = this;
    $.altmodal("删除", "您确定要删除此图片吗?",
    function() {
        $(xx).parent().parent().parent().remove();
        $.post("WxPhotoAction!delPhoto.action", {
            "id": $("#id").val(),
            "url": url
        },
        function(data) {
            if (data) {
              savePhoto();
              var outFileSize=outFile.length;
              if(outFileSize>0){
            	  var alt = "";
                  for (var i in outFile) {
                      alt += outFile[i];
                  }
                  $.altmodal("提示", alt,
                  function() {
                      outFile = new Array();
                      location.reload();
                  });
              }else{
            	  $.sucmodal("提示", "图片删除成功。",2000,function(){
            		  location.reload();
            	  });
              }
            }
        })
    });
}

function xxset(xx) {
    num = xx.indexOf("/files");
    xx = xx.substring(num, xx.length);
    return xx;
}