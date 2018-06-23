<script type="text/javascript">

    var replyObject = null;


    function ajaxToSuerComments(index,commentsId,obj){

        if($(obj).html().trim() == '查看回复'){
            $(obj).html("收起回复")
        }else{
            $(obj).html("查看回复")
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
                    content:$("#songCommentsId").val(),
                    topicType:"1"
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
                    topicType:"0",
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
                    topicType:"1",
                    topicId:'${song.songListId}',
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

    function songListInfoAddPlayList(title,artist,mp3,id){
        var flagSongListHave = false;
        for(indexSong in myPlaylist.original){
            if(myPlaylist.original[indexSong].id == id){
                flagSongListHave = true;
                myPlaylist.play(indexSong*1);
                return;
            }
        }
        if(!flagSongListHave){
            myPlaylist.add({title:title, artist:artist, mp3:mp3, id:id});
            myPlaylist.play(-1);
        }
    }
    
    function jumpToComments() {
        window.location.hash = "songCommentsId";
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
                                    <img src="${song.songPictureUrl}" class="img-full">
                                </div>
                                <div class="caption wrapper-lg">
                                    <div class="text-muted">
                                        <a href="#" onclick="songListInfoAddPlayList('${song.songName}','${song.songSinger}','${song.songUrl}','${song.songListId}')"><i class="fa  fa-play-circle-o" style="font-size: 1.5em">播放</i></a>
                                        &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                                        <a href="#"> <i class="fa  fa-align-justify" style="font-size: 1.5em" onclick="popAddSongToSongSheetList('${song.songListId}')">添加</i></a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="#" onclick="jumpToComments()"><i class="fa  icon-speech" style="font-size: 1.5em">(${page.totalCount})</i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                            <div class="caption wrapper-lg">
                                <div class="text-muted">
                                    <i class="fa  icon-music-tone icon-muted"></i>:<a href="#" class="m-r-sm">${song.songName}</a>
                                    <i class="fa fa-user icon-muted"></i>:<a href="#" class="m-r-sm">${song.songSinger}</a>
                                    <i class="fa fa-clock-o icon-muted"></i>${song.songTime!'-'}
                                    <a href="#" class="m-l-sm"><i class="fa fa-comment-o icon-muted"></i>${page.totalCount}</a>
                                </div>
                                <h2 class="post-title"><a href="#"></a></h2>
                                <div class="post-sum">
                                        ${song.songLyric}
                                </div>
                                <div class="line line-lg"></div>

                            </div>
                            </div>
                        </div>
                    </div>
                    </div>

                    <h4 class="m-t-lg m-b">${page.totalCount} 条歌曲评论</h4>
                    <div class="form-group">
                        <label>歌曲评论</label>
                        <textarea class="form-control" rows="5" placeholder="歌曲评论" id = "songCommentsId"></textarea>
                    </div>
                    <div class="form-group">
                        <button  class="btn btn-success" onclick="ajaxSendSongComments('${song.songListId}')">发送评论</button>
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