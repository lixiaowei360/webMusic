
<script type="text/javascript">

    var questTypeValue = {
        songList:"songList",
        songSheet:"songSheet",
        songSinger:"songSinger",
        songLyric:"songLyric"
    }
    var questType = questTypeValue.songList;

    //发送搜索请求
    function ajaxQuestContentList() {
        if (questType == questTypeValue.songList){
            $.get(
                    "${basePath}/songManagerCtrl/searchSongList.shtml",
                    {
                        songName:$("#queryContentId").val(),
                        pageSize:1,
                        ajaxCallBackFunctionName:"goPageSongList"
                    },
                    function (html) {
                        $("#searchBodyContentId").html(html);
                    }
            )
        }

        if (questType == questTypeValue.songSheet){
            $.get(
                    "${basePath}/songSheetManagerCtrl/searchSongSheetList.shtml",
                    {
                        songSheetName:$("#queryContentId").val(),
                        pageSize:1
                    },
                    function (html) {
                        $("#searchBodyContentId").html(html);
                    }
            )
        }

        if (questType == questTypeValue.songLyric){
            $.get(
                    "${basePath}/songManagerCtrl/searchSongLyrc.shtml",
                    {
                        songLyric:$("#queryContentId").val(),
                        pageSize:1
                    },
                    function (html) {
                        $("#searchBodyContentId").html(html);
                    }
            )
        }
    }

    //分页goPageByAjax
    function goPageSongList(pageNo) {
        $.get(
                "${basePath}/songManagerCtrl/searchSongList.shtml",
                {
                    songName:$("#queryContentId").val(),
                    pageNo:pageNo,
                    pageSize:1,
                    ajaxCallBackFunctionName:"goPageSongList"
                },
                function (html) {
                    $("#searchBodyContentId").html(html);
                }
        )
    }

    //设置查询歌曲url
    function setSearchSongListTabUrl() {
        questType = questTypeValue.songList;
        ajaxQuestContentList();
    }

    //设置歌单url
    function  setSearchSongSheetTabUrl() {
        questType = questTypeValue.songSheet;
        ajaxQuestContentList();
    }

    //设置歌词查询url
    function setSearechSongLyric() {
        questType = questTypeValue.songLyric;
        ajaxQuestContentList();
    }

</script>

<section class="hbox stretch">

    <aside class="aside-lg bg-light lter b-r">
        <section class="vbox">
        </section>
    </aside>

    <section class="vbox" >
            <div class="row"  style="margin-top: 10px;margin-left: 160px;">
                <span class="input-group-btn"><input id="queryContentId" type="text" class="form-control" placeholder="请输入..."></span>
                <span class="input-group-btn">
                    <a class="btn btn-default" onclick="ajaxQuestContentList()" >搜索</a>
                </span>
            </div>

            <div class="row">
                <ul class="nav nav-tabs nav-white center-block">
                    <li class="active"><a href="#" data-toggle="tab" onclick="setSearchSongListTabUrl()">歌曲</a></li>
                    <li class=""><a href="#" data-toggle="tab" onclick="setSearchSongSheetTabUrl()">歌单</a></li>
                    <li class=""><a href="#" data-toggle="tab">用户</a></li>
                    <li class=""><a href="#" data-toggle="tab" onclick="setSearechSongLyric()">歌词</a></li>
                </ul>
            </div>

        <div class="row" id = "searchBodyContentId">
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
        </div>

    </section>
    <aside class="col-lg-3 b-l">
        <section class="vbox">
        </section>
    </aside>
</section>