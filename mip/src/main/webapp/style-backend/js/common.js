var Menu = {
            init: function(){
                var bodyId = $('body').attr('id'), reg = /\w(\d+)-?(\d*)/, match = bodyId.match(reg), mainId, subId,
                    $mainMenu = $('#J_NavList'), $subMenu, CLS = 'current';

                //body没有匹配到id
                if(!match){
                    $mainMenu.find('li:first').addClass(CLS);
                    $('#sub1').show();
                    return;
                }
                mainId = match[1];
                subId = match[2];
                $subMenu = $('#sub' + mainId);
                $mainMenu.find('>li[data-id="' + mainId + '"]').addClass(CLS);
                $subMenu.show();
                //如果打开的页面是子菜单页  高亮对应的子菜单项
                if(subId != ''){
                    $('.sub-nav-list>li').removeClass(CLS);
                    $subMenu.find('[data-id="' + subId + '"]').addClass(CLS);
                }
                $('#J_NavList li').click(function(){
                    

var $subNav = $(this).find(".sub-nav");
  if ($subNav.is(':visible')) {   
    $subNav.slideUp('fast');
    $(this).removeClass('active');
  } else {
    //
    var acti = $('#J_NavList .active');
    $(acti).removeClass('active');
    $(acti).find('.sub-nav').slideUp('fast');
    $(this).addClass('active');
    $subNav.slideDown('fast');
  }

                });                     
            }
        };
        $(function () {
            Menu.init();
        });     
