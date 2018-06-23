<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8" />
    <title>Musik | Web Application</title>
    <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />


    <script src="${basePath}/js/frontDesk/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${basePath}/js/frontDesk/bootstrap.js"></script>
    <!-- App -->
    <script src="${basePath}/js/frontDesk/app.js"></script>
    <script src="${basePath}/js/frontDesk/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${basePath}/js/frontDesk/app.plugin.js"></script>
    <script type="text/javascript" src="${basePath}/js/frontDesk/jPlayer/jquery.jplayer.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/frontDesk/jPlayer/add-on/jplayer.playlist.min.js"></script>

    <link rel="stylesheet" href="${basePath}/js/frontDesk/jPlayer/jplayer.flat.css" type="text/css" />
    <link rel="stylesheet" href="${basePath}/css/frontDesk/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="${basePath}/css/frontDesk/animate.css" type="text/css" />
    <link rel="stylesheet" href="${basePath}/css/frontDesk/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="${basePath}/css/frontDesk/simple-line-icons.css" type="text/css" />
    <link rel="stylesheet" href="${basePath}/css/frontDesk/font.css" type="text/css" />
    <link rel="stylesheet" href="${basePath}/css/frontDesk/app.css" type="text/css" />

    <#--引入layer-->
    <script  src="${basePath}/js/common/layer/layer.js"></script>
    <!--[if lt IE 9]>
        <script src="${basePath}/js/frontDesk/ie/html5shiv.js"></script>
        <script src="${basePath}js/frontDesk/ie/respond.min.js"></script>
        <script src="${basePath}js/frontDesk/ie/excanvas.js"></script>
    <![endif]-->
    <script type="text/javascript">
    function ajaxAddSongToSongSheet(songSheetId,songListId) {

        $.get("${basePath}/songManagerCtrl/saveSongToSongSheetList.shtml",
                {
                    songSheetId:songSheetId,
                    songListId:songListId
                }
                ,function(data){

                    if(data.code != '200'){
                        layer.msg(data.messages.fail);
                        var index = parent.layer.getFrameIndex(window.name);
                        setTimeout(function(){parent.layer.closeAll()}, 1500);
                    }else{
                        layer.msg(data.messages.sucess);
                        var index = parent.layer.getFrameIndex(window.name);
                        setTimeout(function(){parent.layer.closeAll()}, 1500);
                    }

                },'json');
    }
    
    function ajaxAddSheetTop() {
        parent.ajaxAddSheet();
    }
    </script>

</head>
  <body class="">
    <div class="row">
        <div class="col-sm-6">
            <div class="list-group">
                <a href="#" class="list-group-item" onclick="ajaxAddSheetTop();">
                    <i class="fa fa-chevron-right icon-muted"></i>
                    <span class="badge bg-info"></span>
                    <i class="fa fa-chevron-right fa-music"></i> 新增歌单
                </a>
               <#if ssList?exists && ssList?size gt 0 >
                   <#list ssList as it>
                       <a href="#" class="list-group-item" onclick = "ajaxAddSongToSongSheet('${it.songSheetId}','${song.songListId}')" >
                           <span class="badge badge-empty"></span>
                           <i class="fa  fa-music fa-fw"></i> ${it.songSheetName}
                           <i class="fa fa-chevron-right fa-music"></i>
                       </a>
                       </#list>
               </#if>
            </div>
        </div>
    </body>
</html>