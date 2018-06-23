        <div class="tab-content">
                <#if page?exists&&page.list?size gt 0>
                    <#list page.list as it>
                        <ul class="list-group no-radius m-b-none  list-group-lg no-border" onclick="ajaxLookSongListInfo('${it.songListId!'-'}')">
                            <li class="list-group-item" href="#email-content" data-toggle="class:show">
                                <a href="#" class="thumb-xs pull-left m-r">
                                    <img src="${it.songPictureUrl}" class="img-circle">
                                </a>
                                <a href="#" class="clear">
                                    <small class="pull-right">${it.songTime}</small>
                                    <strong class="block">${it.songName}</strong>
                                    <small class="clear">${it.songSinger!'-'}</small>
                                </a>
                            </li>
                        </ul>
                    </#list>
                <#else >
                        <span>没有查询到歌曲</span>
                </#if>
        </div>
        <div class="row pull-left">
            <div class="col-sm-10"></div>
                <div class="pagination pull-right">
                ${page.siAjaxPageHtml}
                </div>
        </div>