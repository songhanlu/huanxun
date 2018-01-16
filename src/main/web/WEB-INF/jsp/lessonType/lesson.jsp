<script type="text/javascript">
    $(function () {
        var timePerLesson = null;
        var lessonArea = null;
        var lessonPrice1 = null;
        var lessonPrice2 = null;
        var lessonDesc = null;


        <!--课程名称模糊查询-->
        $("#queryAllByLessonDescButton").click(function () {
            lessonDesc = $("#lessonDesc").val();
            if (lessonDesc!=null) {
                $("#lessonTypeShow").datagrid("load", {"lessonDesc": lessonDesc});
            }else {
                return;
            }

        })
        <!--外教地区查询-->
        $("#diqv0").click(function () {
            lessonArea=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#diqv1").click(function () {
            lessonArea=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#diqv2").click(function () {
            lessonArea=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#diqv3").click(function () {
            lessonArea=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })



        <!--课时查询-->
        $("#fenzhong0").click(function () {
            timePerLesson=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#fenzhong1").click(function () {
            timePerLesson=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#fenzhong2").click(function () {
            timePerLesson=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#fenzhong3").click(function () {
            timePerLesson=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#fenzhong4").click(function () {
            timePerLesson=$(this).attr("value");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        <!--资费查询-->
        $("#zifei0").click(function () {
            lessonPrice1=$(this).attr("value1");
            lessonPrice2=$(this).attr("value2");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#zifei1").click(function () {
            lessonPrice1=$(this).attr("value1");
            lessonPrice2=$(this).attr("value2");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#zifei2").click(function () {
            lessonPrice1=$(this).attr("value1");
            lessonPrice2=$(this).attr("value2");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#zifei3").click(function () {
            lessonPrice1=$(this).attr("value1");
            lessonPrice2=$(this).attr("value2");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
        $("#zifei").click(function () {
            lessonPrice1=$(this).attr("value1");
            lessonPrice2=$(this).attr("value2");
            $("#lessonTypeShow").datagrid("load",{"lessonPrice1":lessonPrice1,"lessonPrice2":lessonPrice2,"timePerLesson":timePerLesson,"lessonArea":lessonArea});
        })
    })
</script>
