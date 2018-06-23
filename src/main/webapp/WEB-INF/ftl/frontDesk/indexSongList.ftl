          <script type="text/javascript">
              $(document).ready(function(){
              });
          </script>
            <#if page?exists && page.list?size gt 0 >
				<#list page.list as it>
				  <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                      <div class="item">
                          <div class="pos-rlt">
                              <div class="bottom">
                                  <span class="badge bg-info m-l-sm m-b-sm">${it.songTime?default('-')}</span>
                              </div>
                              <div class="item-overlay opacity r r-2x bg-black">
                                  <div class="text-info padder m-t-sm text-sm">
                                      <i class="fa fa-star"></i>
                                      <i class="fa fa-star"></i>
                                      <i class="fa fa-star"></i>
                                      <i class="fa fa-star"></i>
                                      <i class="fa fa-star-o text-muted"></i>
                                  </div>

                                  <div class="center text-center m-t-n" >
                                      <a href="#" data-toggle="class">
                                          <input name="markerId" value="${it.songListId}" style="display: none"/>
                                          <i class="icon-control-play i-2x"  name = "icon-control-play" onclick="addPlayList('plus','${it.songName}','${it.songSinger}','${it.songUrl}','${it.songListId}',this)"></i>
                                          <i class="icon-control-pause i-2x" name = "icon-control-pause"  style="display:none" onclick="addPlayList('pause','${it.songName}','${it.songSinger}','${it.songUrl}','${it.songListId}',this)"></i>
                                      </a>
                                  </div>

                                  <div class="bottom padder m-b-sm">
                                      <a href="#" class="pull-right" >
                                          <i class="fa  fa-info-circle text" onclick="ajaxLookSongListInfo('${it.songListId}')"></i>
                                      </a>
                                      <a href="#" >
                                          <i class="fa fa-plus-circle text" onclick="popAddSongToSongSheetList('${it.songListId}')"></i>
                                      </a>
                                  </div>
                              </div>
                              <div class="top">
                                  <span class="pull-right m-t-sm m-r-sm badge bg-info">${it.songClick?default('-')}</span>
                              </div>
                              <a href="#">
                                  <img src="${it.songPictureUrl?default('-')}" alt="${it.songName?default('-')}"  height="200px" width="190px">
                              </a>
                          </div>
                          <div class="padder-v">
                              <a href="#" class="text-ellipsis">${it.songName?default('-')}</a>
                              <a href="#" class="text-ellipsis text-xs text-muted">${it.songSinger?default('-')}</a>
                          </div>
                          <input id = "indexSongListpageNoId"value="${page.pageNo}" style="display: none"/>
                      </div>
                  </div>
                </#list>
            </#if>