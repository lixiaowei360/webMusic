<script type="text/javascript">
    function indexSongListShow(){
        $.get("${basePath}/indexCtrl/ajaxIndexInfo.shtml",
                {
                },function (html) {
                    $('#bjax-target').html(html);
                    //初始话dom
                    InitializationDom();
                }
        )
    }
    function lookSongSheetInfo(songSheetInfoId) {
        $.get(
                "${basePath}/songSheetManagerCtrl/ajaxLookSongSheetInfo.shtml",
                {
                    songSheetId:songSheetInfoId
                },
                function (html) {
                    $("#bjax-target").html(html);
                }
        )
    }
    function ajaxSongListClassification() {
        $.get("${basePath}/indexCtrl/ajaxSongListClassification.shtml",
                {
                },function (html) {
                    $('#bjax-target').html(html);
                }
        )
    }


    //删除用户创建的歌单
    function deleteUserCreateSongSheet(songSheetId) {
        $.get(
            "${basePath}/songSheetManagerCtrl/cancelSongSheetToUserCreate.shtml",
             {
                 songSheetId:songSheetId
             },function (data) {
                if(data.code == "200"){
                    layer.msg(data.messages.sucess);
                    //请求左侧菜单
                    ajaxLeftMenu();
                }else{
                    layer.msg(data.messages.fail);
                }
            }

            )
    }

    //删除用户收藏的歌单
    function deleteUserCollectionSongSheet(songSheetId) {
        $.get(
            "${basePath}/songSheetManagerCtrl/cancelSongSheetToUserCollect.shtml",
             {
                 sheetId:songSheetId
            },function (data) {
                if(data.code == "200"){
                    layer.msg(data.messages.sucess);
                    //请求左侧菜单
                    ajaxLeftMenu();
                }else{
                    layer.msg(data.messages.fail);
                }
            }
          )
    }

    //编辑歌单信息(跳转到歌单信息)
    function editSongSheetInfo(songSheetId) {
        $.get(
                "${basePath}/songSheetManagerCtrl/editSongSheetInfo.shtml",
                {
                    songSheetId:songSheetId
                },
                function (html) {
                    $("#bjax-target").html(html);
                }
        )
    }

    //鼠标悬停事件监听
    $(function(){
        $("#nav-mark-btn").hover(function(){
            $("#mark-info").show();
        },function(){
            $("#mark-info").hide();
        })
    })
</script>

                    <nav class="nav-primary hidden-xs">
                        <ul class="nav bg clearfix">
                            <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                                发现
                            </li>
                            <li>
                                <a href="#"onclick="indexSongListShow()">
                                    <i class="icon-disc icon text-success"></i>
                                    <span class="font-bold">发现</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" onclick="ajaxSongListClassification()">
                                    <i class="icon-music-tone-alt icon text-info"></i>
                                    <span class="font-bold">音乐分类</span>
                                </a>
                            </li>
                            <#--<li>
                                <a href="events.html">
                                    <i class="icon-drawer icon text-primary-lter"></i>
                                    <b class="badge bg-primary pull-right">6</b>
                                    <span class="font-bold">Events</span>
                                </a>
                            </li>
                            <li>
                                <a href="listen.html">
                                    <i class="icon-list icon  text-info-dker"></i>
                                    <span class="font-bold">Listen</span>
                                </a>
                            </li>-->
                            <#--<li>
                                <a href="video.html" data-target="#content" data-el="#bjax-el" data-replace="true">
                                    <i class="icon-social-youtube icon  text-primary"></i>
                                    <span class="font-bold">Video</span>
                                </a>
                            </li>-->
                            <li class="m-b hidden-nav-xs"></li>
                        </ul>

                        <ul class="nav text-sm">
                            <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                                <span class="pull-right"><a href="#"><i class="icon-plus i-lg" onclick="ajaxAddSheet()"></i></a></span>
                                我的歌单
                            </li>
                            <#if userSongSheet?exists && userSongSheet?size gt 0>
                                <#list userSongSheet as it>
                                     <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                                         <span class="pull-right-lg">
                                             <a href="#" onclick="editSongSheetInfo('${it.songSheetId}')"><i class="fa fa-edit"></i></a>
                                             &nbsp;&nbsp;
                                             <a href="#" onclick="deleteUserCreateSongSheet('${it.songSheetId}')"><i class="fa fa-trash-o"></i></a>
                                         </span>
                                         <span ><a href="#" class="icon-music-tone icon" onclick="lookSongSheetInfo('${it.songSheetId}')">&nbsp;&nbsp;${it.songSheetName}</a></span>
                                     </li>
                                </#list>
                            </#if>
                        </ul>

                        <ul class="nav text-sm">
                            <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                                收藏歌单
                            </li>
                             <#if userSongSheetCollection?exists && userSongSheetCollection?size gt 0>
                                 <#list userSongSheetCollection as it>
                                 <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                                     <span class="pull-right"><a href="#" onclick="deleteUserCollectionSongSheet('${it.songSheetId}')"><i class="fa fa-trash-o"></i></a></span>
                                     <span ><a href="#" onclick="lookSongSheetInfo('${it.songSheetId}')" class="icon-music-tone icon">&nbsp;&nbsp;${it.songSheetName}</a></span>
                                 </li>
                                 </#list>
                             </#if>
                        </ul>
                    </nav>