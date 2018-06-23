
<script type="text/javascript">
    function goPageByAjax(pageNo) {
        $.get(
                "${basePath}/songManagerCtrl/searchSongList.shtml",
                {
                    songName:$("#queryContentId").val(),
                    pageNo:pageNo,
                    pageSize:1
                },
                function (html) {
                    $("#searchBodyContentId").html(html);
                }
        )
    }
</script>
        <div class="tab-content">
                <#if page?exists&&page.list?size gt 0>
                    <#list page.list as it>
                        <ul class="list-group no-radius m-b-none  list-group-lg no-border" onclick="lookSongSheetInfo('${it.songSheetId}')">
                            <li class="list-group-item" href="#email-content" data-toggle="class:show">
                                <a href="#" class="thumb-xs pull-left m-r">
                                    <img src="${it.songPicture!''}" class="img-circle">
                                </a>
                                <a href="#" class="clear">
                                    <small class="pull-right">${it.createTime?string("yyyy-MM-dd")}</small>
                                    <strong class="block">${it.songSheetName!''}</strong>
                                    <small class="clear">${it.user.nickname!'-'}</small>
                                </a>
                            </li>
                        </ul>
                    </#list>
                <#else >
                        <span>没有查询到歌单</span>
                </#if>
        </div>
        <div class="row pull-left">
            <div class="col-sm-10"></div>
                <div class="pagination pull-right">
                ${page.siAjaxPageHtml}
                </div>
        </div>