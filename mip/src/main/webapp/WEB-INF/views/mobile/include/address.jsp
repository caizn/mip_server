<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="d" uri="/WEB-INF/tlds/d.tld"%>


<link rel="stylesheet" href="${ctx }/style-mobile/js/CNAddrArr/scs.min.css"><!-- 地址 -->
<script src="${ctx }/style-mobile/js/CNAddrArr/CNAddrArr.min.js"></script><!-- 地址 -->
<script src="${ctx }/style-mobile/js/CNAddrArr/jquery.scs.min.js"></script><!-- 地址 -->

<script>
//跟地址js相关
var com = {
    /**
         * 通过数组id获取地址列表数组
         *
         * @param {Number} id
         * @return {Array} 
         */ 
    getAddrsArrayById:function(id) {
            var results = [];
            if (addr_arr[id] != undefined)
                addr_arr[id].forEach(function(subArr) {
                    results.push({
                        key: subArr[0],
                        val: subArr[1]
                    });
                });
            else {
                return;
            }
            return results;
    },
    /**
         * 通过开始的key获取开始时应该选中开始数组中哪个元素
         *
         * @param {Array} StartArr
         * @param {Number|String} key
         * @return {Number} 
         */  
    getStartIndexByKeyFromStartArr:function(startArr, key) {
            var result = 0;
            if (startArr != undefined)
                startArr.forEach(function(obj, index) {
                    if (obj.key == key) {
                        result = index;
                        return false;
                    }
                });
            return result;
        }
}
// 跟地址js相关
function myAddrs2(thi,navArr){
            var PROVINCES = [],
                startCities = [],
                startDists = [];
            //Province data，shall never change.
            addr_arr[0].forEach(function(prov) {
                PROVINCES.push({
                    key: prov[0],
                    val: prov[1]
                });
            });
            //init other data.
            var $input = $(thi),
                dataKey = $('#myAddrs').attr("data-key"),
                provKey = 1, //default province 北京
                cityKey = 36, //default city 北京
                distKey = 37, //default district 北京东城区
                distStartIndex = 0, //default 0
                cityStartIndex = 0, //default 0
                provStartIndex = 0; //default 0

            if (dataKey != "" && dataKey != undefined) {
                var sArr = dataKey.split("-");
                if (sArr.length == 3) {
                    provKey = sArr[0];
                    cityKey = sArr[1];
                    distKey = sArr[2];

                } else if (sArr.length == 2) { //such as 台湾，香港 and the like.
                    provKey = sArr[0];
                    cityKey = sArr[1];
                }
                startCities = com.getAddrsArrayById(provKey);
                startDists = com.getAddrsArrayById(cityKey);
                provStartIndex = com.getStartIndexByKeyFromStartArr(PROVINCES, provKey);
                cityStartIndex = com.getStartIndexByKeyFromStartArr(startCities, cityKey);
                distStartIndex = com.getStartIndexByKeyFromStartArr(startDists, distKey);
            }else{
                return false;
            }

            SCS.init({
                navArr: navArr,
                onOk: function(selectedKey, selectedValue) {
                    $input.val(selectedValue).attr("data-key", selectedKey);
                }
            });


            var distScroller = new SCS.scrollCascadeSelect({
                el: "#" + navArr[0].id,
                dataArr: startDists,
                startIndex: distStartIndex
            });

}

function myAddrs(thi,navArr){
            var PROVINCES = [],
                startCities = [],
                startDists = [];
            //Province data，shall never change.
            addr_arr[0].forEach(function(prov) {
                PROVINCES.push({
                    key: prov[0],
                    val: prov[1]
                });
            });
            //init other data.
            var $input = $(thi),
                dataKey = $input.attr("data-key"),
                provKey = 1, //default province 北京
                cityKey = 36, //default city 北京
                distKey = 37, //default district 北京东城区
                distStartIndex = 0, //default 0
                cityStartIndex = 0, //default 0
                provStartIndex = 0; //default 0

            if (dataKey != "" && dataKey != undefined) {
                var sArr = dataKey.split("-");
                if (sArr.length == 3) {
                    provKey = sArr[0];
                    cityKey = sArr[1];
                    distKey = sArr[2];

                } else if (sArr.length == 2) { //such as 台湾，香港 and the like.
                    provKey = sArr[0];
                    cityKey = sArr[1];
                }
                startCities = com.getAddrsArrayById(provKey);
                startDists = com.getAddrsArrayById(cityKey);
                provStartIndex = com.getStartIndexByKeyFromStartArr(PROVINCES, provKey);
                cityStartIndex = com.getStartIndexByKeyFromStartArr(startCities, cityKey);
                distStartIndex = com.getStartIndexByKeyFromStartArr(startDists, distKey);
            }

            SCS.init({
                navArr: navArr,
                onOk: function(selectedKey, selectedValue) {
                    $input.val(selectedValue).attr("data-key", selectedKey);
                    $('#myAddrs2').val('');
                }
            });


            var distScroller = new SCS.scrollCascadeSelect({
                el: "#" + navArr[2].id,
                dataArr: startDists,
                startIndex: distStartIndex
            });
            var cityScroller = new SCS.scrollCascadeSelect({
                el: "#" + navArr[1].id,
                dataArr: startCities,
                startIndex: cityStartIndex,
                onChange: function(selectedItem, selectedIndex) {
                    distScroller.render(com.getAddrsArrayById(selectedItem.key), 0); //re-render distScroller when cityScroller change
                }
            });
            var provScroller = new SCS.scrollCascadeSelect({
                el: "#" + navArr[0].id,
                dataArr: PROVINCES,
                startIndex: provStartIndex,
                onChange: function(selectedItem, selectedIndex) { //re-render both cityScroller and distScroller when provScroller change
                    cityScroller.render(com.getAddrsArrayById(selectedItem.key), 0);
                    distScroller.render(com.getAddrsArrayById(cityScroller.getSelectedItem().key), 0);
                }
            });

}
// 地址
function cNAddrArr(){	
	$("#myAddrs").click(function(even) {
            
            var navArr = [//3 scrollers, and the title and id will be as follows:
            {
                title: "省",
                id: "scs_items_prov"
            }, {
                title: "市",
                id: "scs_items_city"
            }, 
            {
                title: "区",
                id: "scs_items_dist"
            }];
            myAddrs($(this),navArr);
    });

    $("#myAddrs2").click(function(even) { 
            var navArr = [            
            {
                title: "区",
                id: "scs_items_dist"
            }];
            myAddrs2($(this),navArr);
    });
}

</script>