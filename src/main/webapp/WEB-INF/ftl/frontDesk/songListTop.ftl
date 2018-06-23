<#if page?exists && page.list?size gt 0 >
    <#list page.list as it>
        <a href="#" class="list-group-item clearfix" onclick="ajaxLookSongListInfo('${it.songListId}')">
            <span class="pull-right h2 text-muted m-l">${it_index+1}</span>
            <span class="pull-left thumb-sm avatar m-r">
                      <img src="${it.songPictureUrl}" alt="...">
            </span>
            <span class="clear">
                       <span>${it.songName}</span>
                       <small class="text-muted clear text-ellipsis">${it.songSinger}</small>
            </span>
        </a>
    </#list>
</#if>
