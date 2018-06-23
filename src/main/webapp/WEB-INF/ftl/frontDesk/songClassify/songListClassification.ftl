<script type="text/javascript">

    var songClassifyId = "";

    function ajaxSongSheetList(sc,isParent) {
        if (isParent == "yes"){
            return ;
        }
        songClassifyId = sc;
        $.get(
                "${basePath}/songSheetManagerCtrl/ajaxSongSheetList.shtml",
                {
                    songClassifyIds:sc,
                    pageSizeTotal:3
                },
                function (html) {
                    $("#songSheetListId").html(html);
                }
        )
    }
    function goPageByAjax(pageNo){
        $.get(
                "${basePath}/songSheetManagerCtrl/ajaxSongSheetList.shtml",
                {
                    songClassifyIds:songClassifyId,
                    pageSizeTotal:3,
                    pageNo:pageNo
                },
                function (html) {
                    $("#songSheetListId").html(html);
                }
        )
    }
    $(document).ready(function(){
        ajaxSongSheetList("");
    });


</script>
    <section class="hbox stretch">
        <!-- side content -->
        <aside class="aside bg-light dk" id="sidebar">
            <section class="vbox animated fadeInUp">
                <section class="scrollable hover">
                    <div class="list-group no-radius no-border no-bg m-t-n-xxs m-b-none auto">
                        <ul class="list-group">
                        <#if songClassifyList?exists && songClassifyList?size gt 0 >
                            <#list songClassifyList as sc>
                                <li class="list-group-item">
                                    <a href="#" onclick="ajaxSongSheetList('${sc.songClassifyId}','${sc.isParent?string('yes', 'no')}')" style="margin-left:${(sc.hierarchy-1)*30}px">
                                        <#if sc.isParent == false>
                                            <span class="badge pull-right">${sc.songSheetNo}</span>
                                        </#if>
                                        ${sc.songClassifyName!'-'}
                                    </a>
                                </li>
                            </#list>
                        </ul>
                    </#if>
                    </div>
                </section>
            </section>
        </aside>
        <!-- / side content -->
        <section id="songSheetListId">
        </section>
    </section>