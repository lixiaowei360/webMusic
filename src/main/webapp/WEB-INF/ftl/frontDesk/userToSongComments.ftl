<#if page?exists && page.list?size gt 0 >
    <#list page.list as it>
                        <article id="comment-id-1" class="comment-item" >
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
                                    <span class="text-muted text-xs block m-t-xs">
                                        ${it.createTime?string("yyyy-MM-dd HH:mm")}
                                    </span>

                                    <div class="bottom padder m-b-sm">
                                        <a href="#" class="pull-right" style="margin-right: 10px" onclick="replyUserMessage('${it.commentsId}',this,'${it.fromUid}','${it.fromUser.nickname}')">
                                            <i class="fa   fa-comments text"></i>
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
                        <div id="${it.uniqueMarker}"></div>
    </#list>
</#if>
<#if page?exists>
						<div class="pagination pull-left" >
                            ${page.siAjaxPageHtml}
                        </div>
</#if>