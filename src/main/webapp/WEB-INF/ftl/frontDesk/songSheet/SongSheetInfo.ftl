<script type="text/javascript">

    var replyObject = null;

    function ajaxToSuerComments(index,commentsId,obj){

        if($(obj).html().trim() == '查看回复'){
            $(obj).html("收起回复");
        }else{
            $(obj).html("查看回复");
        }
        //判断
        if($("#"+index).html().length  != 0) {
            $("#"+index).html("");
            return true;
        }

      $.get(
            "${basePath}/commentsManagerCtr/lookCommentsTalk.shtml",
                {
                    topicType:"0",
                    topicId:commentsId,
                    pageSize:1,
                    ajaxCallBackFunctionName:index,
                    uniqueMarker:index                              //评论标记发送过去
                },
                function (html) {
                    $("#"+index).html(html);
                }
        )
    }

    function ajaxSendSongComments(songListId){
        $.post(
            "${basePath}/commentsManagerCtr/saveSongComments.shtml",
                {
                    topicId:songListId,
                    content:$("#songCommentId").val(),
                    topicType:'${commentType!'-'}'
                },
                function (data) {
                    if(data.code != '200'){
                        layer.msg(data.messages.fail);
                    }else{
                        layer.msg(data.messages.sucess);
                        goPageByAjax(1);
                    }
                },
                'json'
        )
    }

    function ajaxSendUserToUserComments(obj){
        $.post(
                "${basePath}/commentsManagerCtr/saveSongComments.shtml",
                {
                    topicId:$(obj).parent().parent().find("input[name='commentId']").val(),
                    content:$(obj).parent().parent().find("textarea[name='userName']").val(),
                    topicType:'0',//用户类型是0,回复歌曲类型是1,回复歌单类型是2
                    toUid:$(obj).parent().parent().find("input[name='toUserId']").val()
                },
                function (data) {
                    if(data.code != '200'){
                        layer.msg(data.messages.fail);
                    }else{
                        layer.msg(data.messages.sucess);
                        goPageByAjax(1);
                    }
                },
                'json'
        )
    }

    function goPageByAjax(pageNo){
        $.get(
                "${basePath}/commentsManagerCtr/lookCommentsPage.shtml",
                {
                    topicType:'${commentType!'-'}',
                    topicId:'${(songSheet.songSheetId)!'-'}',
                    pageSize:3,
                    pageNo:pageNo,
                    ajaxCallBackFunctionName:"goPageByAjax"
                },
                function (html) {

                    $("#commentToSongId").html(html);
                }
        )
    }
    function  replyUserMessage(commentId,obj,toUserId,nickName) {
        if(replyObject != null){
            $(replyObject).empty();
        }
        replyObject = $(obj).parent().parent().parent().find("div[name='replyUser']").html($("#replyUserScriptId").html());
        $(replyObject).find("input[name='toUserId']").val(toUserId);
        $(replyObject).find("input[name='commentId']").val(commentId);
        $(replyObject).find("textarea[name='userName']").attr("placeholder","回复"+nickName);
    }

    //播放所有歌曲,ajax请求歌单下的所有歌曲
    function songListInfoAddPlayList(songSheetId){
        //请求歌单下的所有歌曲
        $.get(
            "${basePath}/songSheetManagerCtrl/getSheetSongLists.shtml",
                {
                    songSheetId:songSheetId
                },
                function (data) {
                    var index = 0;
                    for (index in data.returnObj){
                        var flagSongListHave = false;
                        var indexSong = 0;
                        for(indexSong in myPlaylist.original){
                            if(myPlaylist.original[indexSong].id == data.returnObj[index].songListId){
                                flagSongListHave = true;
                                break;
                            }
                        }
                        if(!flagSongListHave){
                            myPlaylist.add({title:data.returnObj[index].songName, artist:data.returnObj[index].songSinger, mp3:data.returnObj[index].songUrl, id:data.returnObj[index].songListId});
                        }
                    }
                    myPlaylist.play(-1);

                }
        )


    }

    //收藏歌单,如果是用户自己创建的歌单可,收藏歌单是灰色的,已经收藏过的歌单也应该是灰色的
    function addSongSheetToUserCollect(songSheetId,iCollect) {
      $.get(
          "${basePath}/songSheetManagerCtrl/addSongSheetToUserCollect.shtml",
              {
                  sheetId:songSheetId
              },
              function (data) {
                  if(data.code == "200"){   //把收藏状态改为已收藏
                      $("#alreadCollectionId").removeAttr("style");
                      $("#collectionId").attr("style","display:none");
                      layer.msg(data.messages.sucess);
                      //请求左侧菜单
                      ajaxLeftMenu();
                  }else{
                      layer.msg(data.messages.fail);
                  }
              }
      )
    }
    //跳转到指定页面
    function jumpToComments() {
        window.location.hash = "songCommentId";
        window.location = window.location;
    }
</script>

<script type="text/template" id = "replyUserScriptId">
    <textarea class="form-control" rows="5" placeholder="回复用户" name = "userName"></textarea>
    <div class="form-group">
        <input name="commentId" style="display: none">
        <input name="toUserId" style="display: none">
        <button  class="btn btn-success" onclick="ajaxSendUserToUserComments(this)">发送评论</button>
    </div>
</script>

    <section class="vbox">
        <section class="scrollable wrapper-lg">
            <div class="row">
                <div class="col-sm-9">
                    <div class="row">
                    <div class="blog-post">
                        <div class="post-item">
                            <div class="col-sm-6">
                                <div class="post-media">
                                    <img src="${(songSheet.songPicture)!'-'}" class="img-full">
                                </div>
                            </div>
                            <div class="col-sm-6">

                            <div class="caption wrapper-lg">

                                <div class="m-b">
                                    <div class="btn-group btn-group-justified">
                                        <div class="text-muted">
                                            <i class="fa   fa-tags icon-muted" style="font-size: 1.8em"><a href="#" class="m-r-sm">${(songSheet.songSheetName)!'-'}</a></i>
                                        </div>
                                    </div>
                                </div>

                                <div class="m-b">
                                    <div class="btn-group btn-group-justified">
                                        <div class="text-muted">
                                            <a href="#" class="m-r-sm" ></a><i class="fa fa-user " style="font-size: 1em">${(songSheet.user.nickname)!'-'}</i></a>
                                            <a href="#" class="m-l-sm" ><i class="fa icon-clock" style="font-size: 1em">${(songSheet.createTime?string("yyyy-MM-dd"))!'-'}</i></a>
                                        </div>
                                    </div>
                                </div>

                                <div class="m-t-md m-b">
                                    <div class="text-muted">
                                        <a href="#" onclick="songListInfoAddPlayList('${(songSheet.songSheetId)!'-'}')"><i class="fa  fa-play-circle-o" style="font-size: 1.2em">全部播放</i></a>
                                        &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                                        <a href="#" id = "alreadCollectionId" ${songSheet.iCollect?string('','style="display:none"')}  class="icon-muted">
                                                <i class="fa  fa-align-justify" style="font-size: 1.2em"  >
                                                已收藏
                                                </i>
                                        </a>
                                        <a href="#" id = "collectionId"  ${songSheet.iCollect?string('style="display:none"','')}  onclick="addSongSheetToUserCollect('${songSheet.songSheetId}')">
                                            <i class="fa  fa-align-justify" style="font-size: 1.2em"  >
                                                收藏
                                            </i>
                                        </a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="#" onclick="jumpToComments()"><i class="fa  icon-speech" style="font-size: 1.2em">(${page.totalCount})</i></a>
                                    </div>
                                </div>
                                <div class="post-sum">
                                        ${(songSheet.songIntroduce)!'-'}
                                </div>
                                <div class="line line-lg"></div>

                            </div>
                            </div>
                        </div>
                    </div>
                    </div>

                    <section class="panel panel-default">
                        <header class="panel-heading">
                            <span class="label bg-danger pull-right m-t-xs">${slPage.totalCount!'-'}首歌曲</span>
                            歌曲列表
                        </header>
                        <table class="table table-striped m-b-none">
                            <thead>
                            <tr>

                                <th>歌曲标题</th>
                                <th>歌手</th>
                                <th>时长</th>
                                <th>发布时间</th>
                                <th>操作</th>
                                <th style="width:70px;"></th>
                            </tr>
                            </thead>
                            <tbody>

                            <#if slPage?exists && slPage.list?size gt 0>

                                <#list slPage.list as it>
                                    <tr>
                                        <td>
                                            ${it.songName}
                                        </td>
                                        <td>${it.songSinger}</td>
                                        <td>${it.songTime}</td>
                                        <td> ${(it.releaseTime?string("yyyy-MM-dd"))!'-'}</td>
                                        <td>
                                            <a href="#" onclick="addSongToSongListPlay('plus','${it.songName}','${it.songSinger}','${it.songUrl}','${it.songListId}')">
                                            <i class="icon-control-play" ></i>
                                            </a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="#" >
                                            <i class="fa  fa-info-circle text" onclick="ajaxLookSongListInfo('${it.songListId}')"></i>
                                            </a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="#" >
                                                <i class="fa fa-plus-circle text" onclick="popAddSongToSongSheetList('${it.songListId}')"></i>
                                            </a>
                                        </td>
                                        <td class="text-right">
                                            <div class="btn-group">
                                                <ul class="dropdown-menu pull-right">
                                                    <li><a href="#">Action</a></li>
                                                    <li><a href="#">Another action</a></li>
                                                    <li><a href="#">Something else here</a></li>
                                                    <li class="divider"></li>
                                                    <li><a href="#">Separated link</a></li>
                                                </ul>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </section>


                    <h4 class="m-t-lg m-b">${page.totalCount} 条歌曲评论</h4>
                    <div class="form-group">
                        <label>歌曲评论</label>
                        <textarea class="form-control" rows="5" placeholder="歌曲评论"  id = "songCommentId"></textarea>
                    </div>
                    <div class="form-group">
                        <button  class="btn btn-success" onclick="ajaxSendSongComments('${songSheet.songSheetId}')">发送评论</button>
                    </div>

                    <h5 class="m-t-lg m-b">精彩评论</h5>
              <section class="comment-list block" id = "commentToSongId">
               <#if page?exists && page.list?size gt 0 >
                   <#list page.list as it>
                        <article id="comment-id-1" class="comment-item">
                            <a class="pull-left thumb-sm">
                                <img src="${it.fromUser.headPicUrl!'-'}" class="img-circle">
                            </a>
                            <section class="comment-body m-b">
                                <header>
                                    <a href="#"><strong>${it.fromUser.nickname!'-'}</strong></a>
                                    <#if it.replyNo == 0>
                                        <label class="label bg-info m-l-xs" id ="ajax">无回复</label>
                                    <#else>
                                        <label class="label bg-info m-l-xs" id ="ajax" onclick="ajaxToSuerComments('${it.uniqueMarker}','${it.commentsId}',this)">查看回复</label>
                                    </#if>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <div class="bottom padder m-b-sm">
                                        <a href="#" class="pull-right" style="margin-right: 10px" onclick="replyUserMessage('${it.commentsId}',this,'${it.fromUid}','${it.fromUser.nickname}')">
                                            <i class="fa   fa-comments text"></i>
                                        </a>

                                        <a href="#" class="pull-right">
                                            ${it.createTime?string("yyyy-MM-dd HH:mm")}发表
                                        </a>

                                    </div>
                                </header>
                                <div class="m-t-sm">${it.content!'-'}</div>

                                <div name ='replyUser'></div>

                            </section>
                        </article>
                        <div id="${it.uniqueMarker}"></div>
                   </#list>
               </#if>
               <#if page?exists>
						<div class="pagination pull-left" >
                            ${page.siAjaxPageHtml}
                        </div>
               </#if>
                </section>

                </div>
                <#--<div class="col-sm-3">
                    <h5 class="font-bold">分类</h5>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="#">
                                <span class="badge pull-right">15</span>
                                Photograph
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <span class="badge pull-right">30</span>
                                Life style
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <span class="badge pull-right">9</span>
                                Food
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <span class="badge pull-right">4</span>
                                Travel world
                            </a>
                        </li>
                    </ul>
                    <div class="tags m-b-lg l-h-2x">
                        <a href="#" class="label bg-primary">Bootstrap</a> <a href="#" class="label bg-primary">Application</a> <a href="#" class="label bg-primary">Apple</a> <a href="#" class="label bg-primary">Less</a> <a href="#" class="label bg-primary">Theme</a> <a href="#" class="label bg-primary">Wordpress</a>
                    </div>
                    <h5 class="font-bold">Recent Posts</h5>
                    <div>
                        <article class="media">
                            <a class="pull-left thumb thumb-wrapper m-t-xs">
                                <img src="images/m1.jpg">
                            </a>
                            <div class="media-body">
                                <a href="#" class="font-semibold">Bootstrap 3: What you need to know</a>
                                <div class="text-xs block m-t-xs"><a href="#">Travel</a> 2 minutes ago</div>
                            </div>
                        </article>
                        <div class="line"></div>
                        <article class="media m-t-none">
                            <a class="pull-left thumb thumb-wrapper m-t-xs">
                                <img src="images/m2.jpg">
                            </a>
                            <div class="media-body">
                                <a href="#" class="font-semibold">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</a>
                                <div class="text-xs block m-t-xs"><a href="#">Design</a> 2 hours ago</div>
                            </div>
                        </article>
                        <div class="line"></div>
                        <article class="media m-t-none">
                            <a class="pull-left thumb thumb-wrapper m-t-xs">
                                <img src="images/m3.jpg">
                            </a>
                            <div class="media-body">
                                <a href="#" class="font-semibold">Sed diam nonummy nibh euismod tincidunt ut laoreet</a>
                                <div class="text-xs block m-t-xs"><a href="#">MFC</a> 1 week ago</div>
                            </div>
                        </article>
                    </div>
                </div>-->
            </div>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>