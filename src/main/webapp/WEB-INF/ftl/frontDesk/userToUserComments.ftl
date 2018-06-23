<script type="text/javascript">
    function ${page.ajaxCallBackFunctionName}(pageNo){
        $.get(
                "${basePath}/commentsManagerCtr/lookCommentsTalk.shtml",
                {
                    topicType:"0",
                    topicId:'${comments.topicId}',
                    pageSize:1,
                    pageNo:pageNo,
                    ajaxCallBackFunctionName:'${page.ajaxCallBackFunctionName}',
                    uniqueMarker:'${comments.uniqueMarker}'
                },
                function (html) {
                    $("#"+'${comments.uniqueMarker}').html(html);
                }
        )
    }
</script>
<#if page?exists && page.list?size gt 0 >
    <#list page.list as it>
                <article id="comment-id-1" class="comment-item" style="margin-left: ${it.leve*46}px;">
                    <a class="pull-left thumb-sm">
                        <img src="${it.fromUser.headPicUrl!'-'}" class="img-circle">
                    </a>
                    <section class="comment-body m-b">
                        <header>
                            <a href="#"><strong>${it.fromUser.nickname!'-'}</strong></a>
                                 <label class="label bg-info m-l-xs" id ="ajax">回复</label>
                            <a href="#"><strong>${it.toUser.nickname!'-'}</strong></a>
                            <span class="text-muted text-xs block m-t-xs">
                                ${it.createTime?string("yyyy-MM-dd HH:mm")}
                            </span>

                            <div class="bottom padder m-b-sm">
                                <a href="#" class="pull-right"   onclick="replyUserMessage('${it.commentsId}',this,'${it.fromUid}','${it.fromUser.nickname}')">
                                    <i class="fa  fa-comments text"></i>
                                </a>

                                <a href="#" class="pull-right">
                                    ${it.createTime?string("yyyy-MM-dd HH:mm")}发表
                                </a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                        </header>

                        <div class="m-t-sm">${it.content!'-'}</div>

                        <div name ='replyUser'></div>

                    </section>
                </article>
            <div id="${it.uniqueMarker}">
    </#list>
</#if>
<div class="row">
    <div class="col-sm-10"></div>
        <div class="pagination pull-right">
        ${page.siAjaxPageHtml}
        </div>
</div>

