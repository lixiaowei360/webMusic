<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>Musik | Web Application</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />

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

    <link rel="stylesheet" href="${basePath}/css/uedit/umeditor.css" type="text/css"/>
    <script src="${basePath}/js/uedit/umeditor.config.js"></script>
    <script src="${basePath}/js/uedit/editor_api.js"></script>
    <script src="${basePath}/js/uedit/zh-cn.js"></script>

    <#--上传文件资源-->
    <script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput.min.js"></script>
    <script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput_locale_zh.js"></script>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/fileinput.min.css" rel="stylesheet"/>
    <#--<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>-->
    <script type="text/javascript">
        var myPlaylist = null; //全局变量
        var indexBottom = null;//layer关闭索引
        var preSongListObject = null;//播放列表中的object
        $(document).ready(function(){
            myPlaylist = new jPlayerPlaylist({
                jPlayer: "#jplayer_N",
                cssSelectorAncestor: "#jp_container_N"
            }, [

            ], {
                playlistOptions: {
                    enableRemoveControls: true,
                    autoPlay: true
                },
                swfPath: "js/jPlayer",
                supplied: "webmv, ogv, m4v, oga, mp3",
                smoothPlayBar: true,
                keyEnabled: true,
                audioFullScreen: true
            });
            $(document).on($.jPlayer.event.pause, myPlaylist.cssSelector.jPlayer,  function(){
                $('.musicbar').removeClass('animate');
                $('.jp-play-me').removeClass('active');
                $('.jp-play-me').parent('li').removeClass('active');
            });
            $(document).on($.jPlayer.event.play, myPlaylist.cssSelector.jPlayer,  function(){
                $('.musicbar').addClass('animate');
                ajaxAddClicksSongList(myPlaylist.original[myPlaylist.current].id);//播放
            });

            InitializationDom();
        });
        function InitializationDom() {
            $.get("${basePath}/indexCtrl/ajaxIndexInfo.shtml",
                    {
                    },function (html) {
                        $('#bjax-target').html(html);
                        //请求页面推荐歌曲
                        ajaxIndexSongList(null);
                        //请求左侧菜单
                        ajaxLeftMenu();
                        //请求新歌发布任务
                        ajaxNewSongList();
                        //请求歌曲排行榜
                        ajaxSongTopTen();
                        //请求查询头部信息
                        ajaxSearchSongInfo();

                    }
            )
        }
        

        function ajaxSearchSongInfo() {
            $.get(
                    "${basePath}/indexCtrl/ajaxSearchSongInfo.shtml",
                    {
                    },
                    function (html) {
                        $("#indexSearchSongId").html(html);
                    }
            )
        }

        function ajaxSongTopTen() {
            $.get(
                    "${basePath}/songManagerCtrl/ajaxSongTopTen.shtml",
                    {
                        pageSize:10,
                        pageNo:1
                    },
                    function (html) {
                        $("#songTopId").html(html);
                    }
            )
        }

        function ajaxAddClicksSongList(songListId) {
            $.get(
                    "${basePath}/songManagerCtrl/ajaxAddClicksSongList.shtml",
                    {
                        songListId:songListId
                    },
                    function (data) {
                        if(data.code != "200"){
                            layer.message("播放量累加失败");
                        }
                    }
            )
        }

        function ajaxNewSongList(){
            $.get(
                    "${basePath}/songManagerCtrl/indexNewSong.shtml",
                    {
                        pageSize:10,
                        pageNo:1,
                    },
                    function (html) {
                        $("#newSongReleaseId").html(html);
                    }
            )
        }

        function ajaxIndexSongList(obj){
            var pageNo = 0;
            if(typeof  $("#indexSongListpageNoId") == 'undefined'  || $("#indexSongListpageNoId").length<1) {
                pageNo = 0;
            }else{
                 pageNo = $("#indexSongListpageNoId").val();
            }
            $.get(
                    "${basePath}/songManagerCtrl/indexSongList.shtml",
                    {
                        pageSize:1,
                        pageNo:pageNo*1+1,
                    },
                    function (html) {
                        $("#indexSongListId").html(html);
                    }
            )

            if(obj != null){
                setTimeout(function(){
                },2000);
            }
        }

        function ajaxLeftMenu(){
            $.get(
                    "${basePath}/indexCtrl/leftMenu.shtml",
                    {
                    },
                    function (html) {
                        $("#leftMenuId").html(html);
                    }
            )
        }

        function refreshIndexSongList() {
            $.get(
                    "${basePath}/songManagerCtrl/indexSongList.shtml",
                    {
                        pageSize:1,
                        pageNo:++pageNo,
                    },
                    function (html) {
                        $("#indexSongListId").html(html);
                    }
            )
        }

        function addPlayList(type,title,artist,mp3,id,obj){ //添加歌曲到歌单中
            //jplayui设置
            //点击播放按钮以及重置播放按钮状态
            if(preSongListObject == null){
            }else if( id != $(preSongListObject).parent().find("input[name='markerId']").val()){
                $(preSongListObject).parent().find("[name='icon-control-play']").removeAttr("style");
                $(preSongListObject).parent().find("[name='icon-control-pause']").attr("style","display:none");
            }else if($(preSongListObject).attr("name") == $(obj).attr("name")){
                $(preSongListObject).parent().find("[name='icon-control-play']").removeAttr("style");
                $(preSongListObject).parent().find("[name='icon-control-pause']").attr("style","display:none");
            }

            if(type == "plus"){
                $(obj).parent().find("[name='icon-control-play']").attr("style","display:none");
                $(obj).parent().find("[name='icon-control-pause']").removeAttr("style");

            }else{
                $(obj).parent().find("[name='icon-control-play']").removeAttr("style");
                $(obj).parent().find("[name='icon-control-pause']").attr("style","display:none");

            }
            preSongListObject = obj;

            //添加歌曲到jplay中
            addSongToSongListPlay(type,title,artist,mp3,id);
        }

        function addSongToSongListPlay(type,title,artist,mp3,id){
            //计算歌曲在歌单中的位置
            var flagSongListHave = false;
            var indexSong = 0;
            for(indexSong in myPlaylist.original){
                if(myPlaylist.original[indexSong].id == id){
                    flagSongListHave = true;
                    break;
                }
            }
            if(type == "plus"){
                //添加歌单
                if(!flagSongListHave){
                    myPlaylist.add({title:title, artist:artist, mp3:mp3, id:id});
                    myPlaylist.play(-1);
                } else{
                    myPlaylist.play(indexSong*1);
                }
            }else{
                myPlaylist.pause();
            }

        }
        function popAddSongToSongSheetList(songListId){//弹出添加歌单中进行添加歌曲到歌单,在下边中保存歌曲
            //设置位置
            index = layer.open({
                type: 2,
                area:['350px', '400px'],
                title:'请选择',
                skin: 'layui-layer-demo', //加上边框
                content: '${basePath}/songManagerCtrl/lookUserSongSheetView.shtml?songListId='+songListId
            });
            layer.style(index, {
                top: '20%'
            });
        }

        function ajaxAddSheet() {//弹出添加歌单表单,是在添加歌曲到歌单中调用的.
           indexBottom =  layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['520px', '300px'], //宽高
                content: $("#songSheetId").html()
            });
        }

        function ajaxSongSheetName() {//添加歌单
            if($("#songSheetNameId").val() == ''){
                layer.msg('请填写歌单');
            }
            $.post("${basePath}/songSheetManagerCtrl/addSongSheetName.shtml",
                    {
                        songSheetName:$("#songSheetNameId").val()
                    }
                    ,function(data){

                        if(data.code != '200'){
                            layer.msg(data.messages.fail);
                            setTimeout(function(){layer.closeAll()}, 1500);
                        }else{
                            layer.msg(data.messages.sucess);
                            setTimeout(function(){layer.closeAll()}, 1500);
                        }
                    },'json');
        }

        function ajaxLookSongListInfo(songListId){//查看歌曲的详情,并跳转到歌曲详情页面
            $.get("${basePath}/songManagerCtrl/ajaxLookSongListInfo.shtml",
                  {
                      songListId:songListId,
                      pageSize:3
                  },function (html) {
                        $('#bjax-target').html(html);
                    }
            )
        }
    </script>

    <script id = "songSheetId" type="text/template">
        <section class="panel panel-default">
            <header class="panel-heading font-bold">添加歌单</header>
            <div class="panel-body">
                <form class="bs-example form-horizontal">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">歌单</label>
                        <div class="col-lg-10">
                            <input id = "songSheetNameId" class="form-control" placeholder="歌单名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <button  class="btn btn-sm btn-default" onclick="ajaxSongSheetName()">保&nbsp存</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </script>

</head>
<body class="">
        <#--搜索栏-->
          <section class="vbox" >
              <header class="bg-white-only header header-md navbar navbar-fixed-top-xs" id="indexSearchSongId">
              </header>
           <section>

        <#--左侧菜单-->
          <section class="hbox stretch"  >
              <aside class="bg-black dk  aside hidden-print" id="nav" >
                      <section class="vbox">
                      <section class="w-f-md scrollable">
                          <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2" id="leftMenuId">

                          </div>
          </section>

            <#--左下角的页面-->
            <footer class="footer hidden-xs no-padder text-center-nav-xs">
              <div class="bg hidden-xs ">
                  <div class="dropdown dropup wrapper-sm clearfix">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <span class="thumb-sm avatar pull-left m-l-xs">                        
                        <img src="${token.headPicUrl!''}" class="dker" alt="...">
                        <i class="on b-black"></i>
                      </span>
                      <span class="hidden-nav-xs clear">
                        <span class="block m-l">
                          <strong class="font-bold text-lt">${token.nickname}</strong>
                          <b class="caret"></b>
                        </span>
                        <span class="text-muted text-xs block m-l">Art Director</span>
                      </span>
                    </a>
                    <#--<ul class="dropdown-menu animated fadeInRight aside text-left">
                      <li>
                        <span class="arrow bottom hidden-nav-xs"></span>
                        <a href="#">设置</a>
                      </li>
                      <li>
                        <a href="profile.html">简介</a>
                      </li>
                      <li>
                        <a href="#">
                          <span class="badge bg-danger pull-right">3</span>
                          通知
                        </a>
                      </li>
                      <li>
                        <a href="docs.html">帮助</a>
                      </li>
                      <li class="divider"></li>
                      <li>
                        <a href="modal.lockme.html" data-toggle="ajaxModal" >退出</a>
                      </li>
                    </ul>-->
                  </div>
                </div>
            </footer>
          </section>
        </aside>

        <!-- /.aside -->
        <section id="content">
          <section class="hbox stretch">
            <section>
              <section class="vbox">

                <#--请求的主题内容,变动其实只有这一个模块改变,其他的都是相对固定的页面-->
                <section class="scrollable padder-lg w-f-md" id="bjax-target">
                </section>


                <footer class="footer bg-dark">
                  <div id="jp_container_N">
                    <div class="jp-type-playlist">
                      <div id="jplayer_N" class="jp-jplayer hide"></div>
                      <div class="jp-gui">
                        <div class="jp-video-play hide">
                          <a class="jp-video-play-icon">play</a>
                        </div>
                        <div class="jp-interface">
                          <div class="jp-controls">
                            <div><a class="jp-previous"><i class="icon-control-rewind i-lg"></i></a></div>
                            <div>
                              <a class="jp-play"><i class="icon-control-play i-2x"></i></a>
                              <a class="jp-pause hid"><i class="icon-control-pause i-2x"></i></a>
                            </div>
                            <div><a class="jp-next"><i class="icon-control-forward i-lg"></i></a></div>
                            <div class="hide"><a class="jp-stop"><i class="fa fa-stop"></i></a></div>
                            <div><a class="" data-toggle="dropdown" data-target="#playlist"><i class="icon-list"></i></a></div>
                            <div class="jp-progress hidden-xs">
                              <div class="jp-seek-bar dk">
                                <div class="jp-play-bar bg-info">
                                </div>
                                <div class="jp-title text-lt">
                                  <ul>
                                    <li></li>
                                  </ul>
                                </div>
                              </div>
                            </div>
                            <div class="hidden-xs hidden-sm jp-current-time text-xs text-muted"></div>
                            <div class="hidden-xs hidden-sm jp-duration text-xs text-muted"></div>
                            <div class="hidden-xs hidden-sm">
                              <a class="jp-mute" title="静音"><i class="icon-volume-2"></i></a>
                              <a class="jp-unmute hid" title="取消静音">
                                  <i class="icon-volume-off"></i>
                              </a>
                            </div>
                            <div class="hidden-xs hidden-sm jp-volume">
                              <div class="jp-volume-bar dk">
                                <div class="jp-volume-bar-value lter"></div>
                              </div>
                            </div>
                            <div>
                              <a class="jp-shuffle" title="shuffle"><i class="icon-shuffle text-muted"></i></a>
                              <a class="jp-shuffle-off hid" title="shuffle off"><i class="icon-shuffle text-lt"></i></a>
                            </div>
                            <div>
                              <a class="jp-repeat" title="repeat"><i class="icon-loop text-muted"></i></a>
                              <a class="jp-repeat-off hid" title="repeat off"><i class="icon-loop text-lt"></i></a>
                            </div>
                            <div class="hide">
                              <a class="jp-full-screen" title="full screen"><i class="fa fa-expand"></i></a>
                              <a class="jp-restore-screen" title="restore screen"><i class="fa fa-compress text-lt"></i></a>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="jp-playlist dropup" id="playlist">
                        <ul class="dropdown-menu aside-xl dker">
                          <!-- The method Playlist.displayPlaylist() uses this unordered list -->
                          <li class="list-group-item"></li>
                        </ul>
                      </div>
                      <div class="jp-no-solution hide">
                        <span>更新需求</span>
                         为了播放媒体,你需要更新你的浏览器到最新版本,或者跟新你的flash<a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
                      </div>
                    </div>
                  </div>
                </footer>
              </section>
            </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
        </section>
      </section>
    </section>    
  </section>
</body>
</html>