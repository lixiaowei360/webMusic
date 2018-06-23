<section class="vbox">
                <section class="scrollable padder-lg">
                    <h2 class="font-thin m-b">歌单列表</h2>
                    <div class="row row-sm">
                        <#if page?exists && page.list?size gt 0 >
                            <#list page.list as it>
                                <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                                    <div class="item">
                                        <div class="pos-rlt">

                                            <a href="#" class="item-overlay  r r-2x" onclick="lookSongSheetInfo('${it.songSheetId!'-'}')">
                                            </a>

                                            <a href="#">
                                                <img src="${it.songPicture!'-'}" alt="" height="200px" width="152px" >
                                            </a>
                                            <div class="bottom padder  bg-black">
                                                <a href="#" class="pull-right" data-toggle="class">
                                                    <i class="fa fa-play-circle" style="font-size: 1.2em;"></i>
                                                </a>
                                                <a href="#" data-toggle="class">
                                                    <i class="fa  fa-headphones" style="font-size: 1.2em;">${it.songSheetClick!'0'}</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="padder-v">
                                            <a href="track-detail.html" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis">${it.songSheetName!'-'}</a>
                                            <a href="track-detail.html" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted">${it.user.nickname!'-'}</a>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>

                    <div class="row">
                        <div class="col-sm-10"></div>
                        <div class="pagination pull-right">
                        ${page.siAjaxPageHtml}
                        </div>
                    </div>
                </section>
            </section>