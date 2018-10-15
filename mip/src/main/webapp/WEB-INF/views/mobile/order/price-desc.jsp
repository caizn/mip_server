<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收费标准</title>
    <jsp:include page="../include/style.jsp" flush="true"></jsp:include>
    <jsp:include page="../include/jquery.jsp" flush="true"></jsp:include>

    <script type="text/javascript">
        $(function(){
            myOnload();
        });

        // 处理加载
        function myOnload(){
            window.onload=function(){
                $('.wrap').css('visibility','visible');
                $('#onload-X').remove();
            }
        }
    </script>

    <style type="text/css">
        table{width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
            border: 1px solid #ccc;
        }
        tr,td{border: 1px solid #ccc;font-size: 13px;height: 40px;}
        table td:nth-child(1){width: 10%;text-align: center;}
        table td:nth-child(2){width: 55%;}
        table td:nth-child(3){width: 10%;text-align: center;}
        table td:nth-child(4){width: 25%;text-align: center;}

    </style>

</head>
<body class="bgfff">

<div id="onload-X" style="text-align: center;padding:30px">
    <img src="${ctx }/style-mobile/images/onload.gif" style="width:25px;">
</div>

<div class="wrap pdLR10 pdTB15">
    <table class="">
        <tr class="txtC_">
            <td>序号</td>
            <td>服务项目</td>
            <td>单位</td>
            <td>价格</td>
        </tr>
        <th colspan="4" class="txtC">
            给排水维修
        </th>
        <tr>
            <td>1</td>
            <td>PPR给水管爆管维修（外直径小于32mm）明管</td>
            <td>处</td>
            <td>150-300</td>
        </tr>
        <tr>
            <td>2</td>
            <td>PPR给水管爆管维修（外直径小于32mm）砖墙暗管 能明确漏水点，需要查找另计费用</td>
            <td>处</td>
            <td>300-500</td>
        </tr>
        <tr>
            <td>3</td>
            <td>PPR给水管爆管维修（外直径小于32mm）混凝土内暗管 能明确漏水点，需要查找另计费用</td>
            <td>处</td>
            <td>400-600</td>
        </tr>
        <tr>
            <td>4</td>
            <td>PVC排水管维修（Φ110以下）明管，明确漏水点 非高空作业</td>
            <td>处</td>
            <td>200-300</td>
        </tr>
        <th colspan="4" class="txtC">
            洁具水暖五金件（常规安装报价不含材料）
        </th>
        <tr>
            <td>1</td>
            <td>安装混合水龙头（面盆、洗菜盆、淋浴）</td>
            <td>套</td>
            <td>50.00 </td>
        </tr>
        <tr>
            <td>2</td>
            <td>安装单水龙头（拖布池、洗衣机）</td>
            <td>套</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>3</td>
            <td>安装不锈钢水槽（安水龙头，接进水排水，打防霉胶）</td>
            <td>套</td>
            <td>80-120</td>
        </tr>
        <tr>
            <td>4</td>
            <td>安装高压防爆管，三角阀</td>
            <td>个</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>5</td>
            <td>安装座式马桶</td>
            <td>个</td>
            <td>100.00 </td>
        </tr>
        <tr>
            <td>7</td>
            <td>安装蹲便器冲水箱</td>
            <td>个</td>
            <td>100.00 </td>
        </tr>
        <tr>
            <td>9</td>
            <td>安装普通淋浴房（挡水条，玻璃，金属框安装）</td>
            <td>套</td>
            <td>150-300</td>
        </tr>
        <tr>
            <td>10</td>
            <td>安装亚克力浴缸（含浴缸龙头，排水，打胶）</td>
            <td>套</td>
            <td>150-300</td>
        </tr>
        <tr>
            <td>11</td>
            <td>安装铸铁浴缸（含浴缸龙头，排水，打胶）</td>
            <td>套</td>
            <td>300-600</td>
        </tr>
        <tr>
            <td>12</td>
            <td>安装成品浴室柜（洗面柜，镜柜，混水龙头，下水各一个）</td>
            <td>套</td>
            <td>150-300</td>
        </tr>
        <tr>
            <td>13</td>
            <td>安装镜子（1m以下的单体镜子）</td>
            <td>个</td>
            <td>60-100</td>
        </tr>
        <tr>
            <td>14</td>
            <td>安装陶瓷立柱盆（立柱盆，混水龙头，下水各一个）</td>
            <td>套</td>
            <td>120-180</td>
        </tr>
        <tr>
            <td>16</td>
            <td>安装台下盆</td>
            <td>个</td>
            <td>150.00 </td>
        </tr>
        <tr>
            <td>17</td>
            <td>安装台上盆</td>
            <td>个</td>
            <td>100.00 </td>
        </tr>
        <tr>
            <td>18</td>
            <td>安装卫浴五金件（毛巾杆、浴巾架，手纸盒，马桶刷等）</td>
            <td>件</td>
            <td>20-60 </td>
        </tr>
        <tr>
            <td>19</td>
            <td>下水软管换装</td>
            <td>处</td>
            <td>60-100 </td>
        </tr>
        <tr>
            <td>21</td>
            <td>地漏安装（切割，刷防水，安装）</td>
            <td>个</td>
            <td>60-200</td>
        </tr>
        <tr>
            <td>22</td>
            <td>墙内冷热水管PPR内丝弯头取断丝 不打墙</td>
            <td>项</td>
            <td>100-200</td>
        </tr>
        <tr>
            <td>23</td>
            <td>墙内冷热水管PPR内丝弯头取断丝 要打墙换内丝弯头&nbsp;</td>
            <td>项</td>
            <td>300-400</td>
        </tr>
        <tr>
            <td>24</td>
            <td>家庭冷热水管试压测漏检查</td>
            <td>次</td>
            <td>200</td>
        </tr>
        <th colspan="4" class="txtC">
            电气安装人工费（线路铺设到位，瓷砖打孔另算）
        </th>
        <tr>
            <td>1</td>
            <td>筒灯、射灯安装 直径100mm以下&nbsp;</td>
            <td>套</td>
            <td>10--15</td>
        </tr>
        <tr>
            <td>2</td>
            <td>筒灯、射灯安装 直径100-200mm&nbsp;</td>
            <td>套</td>
            <td>15-20</td>
        </tr>
        <tr>
            <td>3</td>
            <td>安装（普通吸顶灯）直径500mm以下</td>
            <td>套</td>
            <td>40-60</td>
        </tr>
        <tr>
            <td>4</td>
            <td>安装（普通吸顶灯）直径500-800mm</td>
            <td>套</td>
            <td>60-80</td>
        </tr>
        <tr>
            <td>5</td>
            <td>安装（普通吸顶灯）直径800mm以上</td>
            <td>套</td>
            <td>80-120</td>
        </tr>
        <tr>
            <td>6</td>
            <td>壁灯安装</td>
            <td>套</td>
            <td>30-50</td>
        </tr>
        <tr>
            <td>7</td>
            <td>镜前灯安装&nbsp;&nbsp;</td>
            <td>套</td>
            <td>30-50</td>
        </tr>
        <tr>
            <td>9</td>
            <td>灯具安装（枝型吊灯）&nbsp;</td>
            <td>头</td>
            <td>10--20</td>
        </tr>
        <tr>
            <td>10</td>
            <td>灯具安装（平板现代花灯）直径600mm以下</td>
            <td>套</td>
            <td>60-80</td>
        </tr>
        <tr>
            <td>11</td>
            <td>水晶类吸顶灯 直径600mm以下</td>
            <td>套</td>
            <td>80-120</td>
        </tr>
        <tr>
            <td>12</td>
            <td>灯具安装（水晶吊灯） 6个头以下</td>
            <td>头</td>
            <td>20-30</td>
        </tr>
        <tr>
            <td>13</td>
            <td>灯具拆旧</td>
            <td>套</td>
            <td>20--50</td>
        </tr>
        <tr>
            <td>14</td>
            <td>LED或霓虹灯带</td>
            <td>米</td>
            <td>4--6</td>
        </tr>
        <tr>
            <td>15</td>
            <td>T4或T5日光灯管</td>
            <td>根</td>
            <td>10--20</td>
        </tr>
        <tr>
            <td>16</td>
            <td>开关插座面板安装</td>
            <td>个</td>
            <td>10.00 </td>
        </tr>
        <tr>
            <td>17</td>
            <td>塑料线盒+开关插座面板安装</td>
            <td>套</td>
            <td>25.00 </td>
        </tr>
        <tr>
            <td>18</td>
            <td>排风扇安装</td>
            <td>套</td>
            <td>100-150</td>
        </tr>
        <tr>
            <td>19</td>
            <td>浴霸安装</td>
            <td>套</td>
            <td>100-150</td>
        </tr>
        <tr>
            <td>20</td>
            <td>储水型电热水器安装</td>
            <td>台</td>
            <td>150-200</td>
        </tr>
        <tr>
            <td>21</td>
            <td>燃气热水器安装</td>
            <td>台</td>
            <td>100-150</td>
        </tr>
        <tr>
            <td>22</td>
            <td>空气能热水器安装</td>
            <td>台</td>
            <td>300-1000</td>
        </tr>
        <tr>
            <td>23</td>
            <td>抽油烟机安装</td>
            <td>台</td>
            <td>150.00 </td>
        </tr>
        <tr>
            <td>25</td>
            <td>家庭线路故障专业工具全面检查</td>
            <td>次</td>
            <td>100-300</td>
        </tr>
        <th colspan="4" class="txtC">
            新装电气穿管布线
        </th>
        <tr>
            <td>1</td>
            <td>明装PVC槽板布线 3根线以下</td>
            <td>米</td>
            <td>20.00 </td>
        </tr>
        <tr>
            <td>2</td>
            <td>暗管开槽&nbsp;&nbsp;宽度30mm以下</td>
            <td>米</td>
            <td>20.00 </td>
        </tr>
        <tr>
            <td>3</td>
            <td>暗管开槽&nbsp;&nbsp;宽度30-50mm</td>
            <td>米</td>
            <td>25.00 </td>
        </tr>
        <tr>
            <td>4</td>
            <td>穿墙孔 直径32mm以下，厚度240mm以下</td>
            <td>个</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>5</td>
            <td>暗装PVC线管布线 3根线以下，不含开槽</td>
            <td>米</td>
            <td>25.00 </td>
        </tr>
        <tr>
            <td>6</td>
            <td>彩钢金属管穿管布线</td>
            <td>米</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>7</td>
            <td>铝合金地面线槽板</td>
            <td>米</td>
            <td>25.00 </td>
        </tr>
        <tr>
            <td>8</td>
            <td>PPR管安装 直径20mm&nbsp;不含开槽</td>
            <td>米</td>
            <td>25.00 </td>
        </tr>
        <tr>
            <td>9</td>
            <td>PPR管安装 直径25mm&nbsp;不含开槽</td>
            <td>米</td>
            <td>25.00 </td>
        </tr>
        <tr>
            <td>10</td>
            <td>PPR管安装 直径32mm&nbsp;不含开槽</td>
            <td>米</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>11</td>
            <td>PVC排水管安装 直径50mm&nbsp;不含开槽</td>
            <td>米</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>12</td>
            <td>PVC排水管安装 直径110mm&nbsp;不含开槽</td>
            <td>米</td>
            <td>35.00 </td>
        </tr>
        <th colspan="4" class="txtC">
            双包（包工包料）起步价400元起
        </th>
        <tr>
            <td>1</td>
            <td>明装PVC槽板或圆管灯线（含1根槽板或圆管2根1.5mm2铜芯线）原则上不用1.5mm2电线</td>
            <td>米</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>2</td>
            <td>明装PVC槽板或圆管灯线（含1根槽板或圆管3根2.5mm2铜芯线）</td>
            <td>米</td>
            <td>35.00 </td>
        </tr>
        <tr>
            <td>3</td>
            <td>明装PVC槽板或圆管插座线（含1根槽板或圆管3根2.5mm2铜芯线）</td>
            <td>米</td>
            <td>40.00 </td>
        </tr>
        <tr>
            <td>4</td>
            <td>明装PVC槽板或圆管空调线（含1根槽板或圆管3根4mm2铜芯线）</td>
            <td>米</td>
            <td>45.00 </td>
        </tr>
        <tr>
            <td>5</td>
            <td>暗装PVC阻燃管灯线（含开槽，1根圆管2根1.5mm2铜芯线）原则上不用1.5mm2电线</td>
            <td>米</td>
            <td>45.00 </td>
        </tr>
        <tr>
            <td>6</td>
            <td>暗装PVC阻燃管灯线（含开槽，1根圆管2根2.5mm2铜芯线）</td>
            <td>米</td>
            <td>50.00 </td>
        </tr>
        <tr>
            <td>7</td>
            <td>暗装PVC阻燃管插座线（含开槽，1根圆管3根2.5mm2铜芯线）</td>
            <td>米</td>
            <td>55.00 </td>
        </tr>
        <tr>
            <td>8</td>
            <td>暗装PVC阻燃管空调线（含开槽，1根圆管3根4mm2铜芯线）</td>
            <td>米</td>
            <td>60.00 </td>
        </tr>
        <tr>
            <td>9</td>
            <td>PVC线盒+五孔插座面板&nbsp;暗装</td>
            <td>套</td>
            <td>35.00 </td>
        </tr>
        <tr>
            <td>10</td>
            <td>PVC线盒+16A空调插座面板&nbsp;暗装</td>
            <td>套</td>
            <td>40.00 </td>
        </tr>
        <tr>
            <td>11</td>
            <td>PPR给水管 直径20mm 含管件&nbsp;不含开槽</td>
            <td>米</td>
            <td>35.00 </td>
        </tr>
        <tr>
            <td>12</td>
            <td>PPR给水管 直径25mm 含管件&nbsp;不含开槽</td>
            <td>米</td>
            <td>40.00 </td>
        </tr>
        <tr>
            <td>13</td>
            <td>PPR给水管 直径32mm 含管件&nbsp;不含开槽</td>
            <td>米</td>
            <td>45.00 </td>
        </tr>
        <tr>
            <td>14</td>
            <td>PVC排水管安装 直径50mm 含管件&nbsp;不含开槽</td>
            <td>米</td>
            <td>50.00 </td>
        </tr>
        <tr>
            <td>15</td>
            <td>PVC排水管安装 直径110mm 含管件不含开槽</td>
            <td>米</td>
            <td>65.00 </td>
        </tr>
        <th colspan="4" class="txtC">
            水工材料费
        </th>
        <tr>
            <td>1</td>
            <td>三角阀</td>
            <td>个</td>
            <td>60 </td>
        </tr>
        <tr>
            <td>2</td>
            <td>金属软管 长600mm以下</td>
            <td>根</td>
            <td>40</td>
        </tr>
        <tr>
            <td>3</td>
            <td>金属软管 长600-800mm</td>
            <td>根</td>
            <td>60</td>
        </tr>
        <tr>
            <td>4</td>
            <td>金属软管 长600-800mm</td>
            <td>根</td>
            <td>80</td>
        </tr>
        <tr>
            <td>5</td>
            <td>面盆翻板下水+下水管</td>
            <td>套</td>
            <td>120 </td>
        </tr>
        <tr>
            <td>6</td>
            <td>玻璃胶300ml 透明 白色 灰色</td>
            <td>支</td>
            <td>30</td>
        </tr>
        <tr>
            <td>7</td>
            <td>耐候胶300ml 黑色 白色 灰色</td>
            <td>支</td>
            <td>30</td>
        </tr>
        <tr>
            <td>8</td>
            <td>结构胶590ml 黑色</td>
            <td>支</td>
            <td>80</td>
        </tr>
        <tr>
            <td>9</td>
            <td>防霉胶300ml 半透明 白色</td>
            <td>支</td>
            <td>50</td>
        </tr>
        <tr>
            <td>10</td>
            <td>单冷水龙头</td>
            <td>个</td>
            <td>60</td>
        </tr>
        <tr>
            <td>11</td>
            <td>混合冷热水水龙头</td>
            <td>个</td>
            <td>150-300</td>
        </tr>

        <th colspan="4" class="txtC">
            电工材料
        </th>
        <tr>
            <td>1</td>
            <td>明装PVC槽板 宽度30mm以下 含管件</td>
            <td>米</td>
            <td>15.00 </td>
        </tr>
        <tr>
            <td>2</td>
            <td>PVC阻燃穿线管 直径20mm以下 含管件</td>
            <td>米</td>
            <td>15.00 </td>
        </tr>
        <tr>
            <td>3</td>
            <td>铝合金地面线槽板</td>
            <td>米</td>
            <td>45.00 </td>
        </tr>
        <tr>
            <td>4</td>
            <td>塑料线盒 普通86型</td>
            <td>个</td>
            <td>10.00 </td>
        </tr>
        <tr>
            <td>5</td>
            <td>五孔插座</td>
            <td>个</td>
            <td>40.00 </td>
        </tr>
        <tr>
            <td>6</td>
            <td>1.5mm2铜芯线&nbsp;&nbsp;原则上不提供</td>
            <td>米</td>
            <td>5.00 </td>
        </tr>
        <tr>
            <td>7</td>
            <td>2.5mm2铜芯线</td>
            <td>米</td>
            <td>8.00 </td>
        </tr>
        <tr>
            <td>8</td>
            <td>4mm2铜芯线</td>
            <td>米</td>
            <td>12.00 </td>
        </tr>
        <tr>
            <td>9</td>
            <td>RVV3*2.5铜芯电缆</td>
            <td>米</td>
            <td>20.00 </td>
        </tr>
        <tr>
            <td>10</td>
            <td>PPR给水管 直径20mm 含管件</td>
            <td>米</td>
            <td>20.00 </td>
        </tr>
        <tr>
            <td>11</td>
            <td>PPR给水管 直径25mm 含管件</td>
            <td>米</td>
            <td>25.00 </td>
        </tr>
        <tr>
            <td>12</td>
            <td>PPR给水管 直径32mm 含管件</td>
            <td>米</td>
            <td>30.00 </td>
        </tr>
        <tr>
            <td>13</td>
            <td>PVC排水管 直径50mm 含管件</td>
            <td>米</td>
            <td>40.00 </td>
        </tr>
        <tr>
            <td>14</td>
            <td>PVC排水管 直径110mm 含管件</td>
            <td>米</td>
            <td>60.00 </td>
        </tr>
    </table>
</div>





</body>
</html>