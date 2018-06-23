
<script type="text/javascript">
function searchSongHeader(){

    $.get("${basePath}/indexCtrl/ajaxIndexInfo.shtml",
            {
            },function (html) {
                $('#bjax-target').html(html);
                //初始话dom
                InitializationDom();
            }
    )
}

function searchContent() {//查询搜索内容

    $.get(
        "${basePath}/indexCtrl/searchSongLists.shtml",
            {
                songName:$("#searchCotenntId").val()
            },
        function (html) {
            $("#bjax-target").html(html);
        }
    )

}

function lookUserInfo() {
    $.get(
            "${basePath}/indexCtrl/lookUserInfo.shtml",
            {
                songName:$("#searchCotenntId").val()
            },
            function (html) {
                $("#bjax-target").html(html);
            }
    )
}
</script>


<div class="navbar-header aside bg-info ">
    <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
        <i class="icon-list"></i>
    </a>
    <a href="#" class="navbar-brand text-lt" onclick="searchSongHeader()">
        <i class="icon-earphones"></i>
        <img src="${basePath}/images/logo.png" alt="." class="hide">
        <span class="hidden-nav-xs m-l-sm">音乐</span>
    </a>
    <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
        <i class="icon-settings"></i>
    </a>
</div>

          <form class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs" role="search">
              <div class="form-group">
                  <div class="input-group">
                        <span class="input-group-btn">
                          <a onclick="searchContent()" href="#" class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></a>
                        </span>
                       <input type="text" id="searchCotenntId" class="form-control input-sm no-border rounded" placeholder="查询歌曲">
                  </div>
              </div>
          </form>

          <div class="navbar-right ">
              <ul class="nav navbar-nav m-n hidden-xs nav-user user">
                  <#--<li class="hidden-xs">
                      <a href="#" class="dropdown-toggle lt" data-toggle="dropdown">
                          <i class="icon-bell"></i>
                          <span class="badge badge-sm up bg-danger count">2</span>
                      </a>
                      <section class="dropdown-menu aside-xl animated fadeInUp">
                          <section class="panel bg-white">
                              <div class="panel-heading b-light bg-light">
                                  <strong>You have <span class="count">2</span> notifications</strong>
                              </div>
                              <div class="list-group list-group-alt">
                                  <a href="#" class="media list-group-item">
                    <span class="pull-left thumb-sm">
                      <img src="${basePath}/images/a0.png" alt="..." class="img-circle">
                    </span>
                                      <span class="media-body block m-b-none">
                      Use awesome animate.css<br>
                      <small class="text-muted">10 minutes ago</small>
                    </span>
                                  </a>
                                  <a href="#" class="media list-group-item">
                    <span class="media-body block m-b-none">
                      1.0 initial released<br>
                      <small class="text-muted">1 hour ago</small>
                    </span>
                                  </a>
                              </div>
                              <div class="panel-footer text-sm">
                                  <a href="#" class="pull-right"><i class="fa fa-cog"></i></a>
                                  <a href="#notes" data-toggle="class:show animated fadeInRight">查看所有通知</a>
                              </div>
                          </section>
                      </section>
                  </li>-->
                  <li class="dropdown" onclick="lookUserInfo()">
                      <a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown">
                          <span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm">
                            <img src="${token.headPicUrl!''}" alt="...">
                          </span>
                        ${token.nickname}<b class="caret"></b>
                      </a>
                      <#--<ul class="dropdown-menu animated fadeInRight">
                          <li>
                              <span class="arrow top"></span>
                              <a href="#" onclick="lookUserInfo()">主页</a>
                          </li>
                          <li>
                              <a href="profile.html">简介</a>
                          </li>
                          <li>
                              <a href="#">
                                  <span class="badge bg-danger pull-right">3</span>
                                  通知
                              </a>
                          </li>
                          <li>
                              <a href="docs.html">帮助</a>
                          </li>
                          <li class="divider"></li>
                          <li>
                              <a href="modal.lockme.html" data-toggle="ajaxModal" >退出</a>
                          </li>
                      </ul>-->
                  </li>
              </ul>
          </div>