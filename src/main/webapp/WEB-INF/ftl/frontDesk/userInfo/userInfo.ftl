
<script type="text/javascript">
    var um = UM.getEditor('myEditor',{
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下七个
        toolbar:['fullscreen source undo redo bold italic underline'],
        //focus时自动清空初始化时的内容
        autoClearinitialContent:true,
        //关闭字数统计
        wordCount:false,
        //关闭elementPath
        elementPathEnabled:false,
        //默认的编辑区域高度
        initialFrameHeight:300
        //更多其他参数，请参考umeditor.config.js中的配置项
    });
    //使用之前首先清理一下uEdit的缓存
    UM.clearCache("myEditor");
    um.ready(function() {
        um.setContent('${user.personalIntro!'-'}');
    });

    function saveUserInfo() {
        $(document).ready(function(){
            var editor= um.getContent();
            console.log(editor);
            $("#myEditor").val(editor);
        });
        $.ajax({
            url: "${basePath}/userInfoManagerCtrl/saveUserInfoManagerCtrl.shtml",
            data: new FormData($('#editUesrInfoId')[0]),
            type: "post",
            dataType: "text",
            cache: true,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false
        }).success(function(html){
            $("#bjax-target").html(html);
            layer.msg("保存成功");
        }).error(function(){
            layer.msg("修改失败");
        });
    }
    $(document).ready(function(){
        initFilePictureInput("filePicture",20,['jpg', 'png','bmp','jpeg']);
    });
    function initFilePictureInput(ctrlName, fileSize,fileType){
        $("#"+ctrlName).fileinput({
            previewFileType:'any',
            showCaption: false,
            language: 'zh', //设置语言
            dropZoneEnabled:true,
            allowedFileExtensions: ['jpg', 'gif', 'png'], //接收的文件后缀
            maxFileCount:1,
            showUpload: false, //是否显示上传按钮
            showCaption: true, //是否显示标题,
            maxFileSize: fileSize * 1000, //单位为kb，如果为0表示不限制文件大小
            browseClass: "btn btn-primary", //按钮样式
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            initialCaption: "请选择上传素材",
            showRemove:false,
            layoutTemplates :{
                actionDelete:'', //去除上传预览的缩略图中的删除图标
                actionUpload:'',//去除上传预览缩略图中的上传图片；
                actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
            },
            initialPreview:[
                '<div class="kv-file-content">'+
                '<img src = "${user.headPicUrl!}"'+
                'class="file-preview-image kv-preview-data"'+
                'title="1.png"'+
                'alt="1.png"'+
                'style="width:auto;height:auto;max-width:100%;max-height:100%;"'+
                '</div>'],
            initialPreviewConfig: [
                {caption: "${user.nickname!}", size: 329892, width: "120px", url: "${user.headPicUrl!}", key: 1}
            ],
            uploadAsync:false
        });
    }
</script>

<section class="scrollable padder">
    <div class="m-b-md">
        <h3 class="m-b-none">修改信息</h3>
    </div>
    <div class="row">
        <div class="col-sm-10">
            <form data-validate="parsley" id="editUesrInfoId" enctype="multipart/form-data">
                <input name = "id" value="${user.id}" style="display: none">
                <section class="panel panel-default">
                    <header class="panel-heading">
                        <span class="h4">用户信息</span>
                    </header>
                    <div class="panel-body">
                        <div class="form-group pull-in clearfix">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label>用户名</label>
                                    <input type="text" class="form-control" placeholder="用户名" data-required="true" name="nickname" value="${user.nickname!'-'}">
                                </div>

                                <div class="form-group">
                                    <label>邮箱</label>
                                    <input type="text" class="form-control" placeholder="邮箱" data-required="true" name="email" value="${user.email!'-'}">
                                </div>

                                <div class="form-group">
                                    <label>密码</label>
                                    <input  type="password"  class="form-control" data-required="true" name ="pswd" value="${user.pswd!'-'}">
                                    </input>
                                </div>


                                <div class="form-group">
                                    <label>个人介绍</label>
                                    <textarea id="myEditor"style="width:480px;height:240px;" name ="personalIntro">${user.personalIntro!'-'}
                                    </textarea>
                                </div>

                                <input style="display: none" name="headPicUrl" value="${user.headPicUrl!}">
                            </div>
                            <div class="col-sm-6">
                                <div class="clearfix text-center m-t">
                                    <div class="inline">
                                        <div class="easypiechart" data-percent="75" data-line-width="5" data-bar-color="#4cc0c1" data-track-Color="#f5f5f5" data-scale-Color="false" data-size="134" data-line-cap='butt' data-animate="1000">
                                            <div class="thumb-lg">
                                                <img src="${user.headPicUrl!'-'}" class="img-circle" alt="...">
                                            </div>
                                        </div>
                                        <div class="h4 m-t m-b-xs">封面</div>
                                        <div class="form-group">
                                            <label for="name">上传头像封面</label>
                                            <div class="file-loading">
                                                <input id="filePicture"  type="file" name = "headPic" >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <footer class="panel-footer text-left bg-light lter">
                        <a class="btn btn-success btn-s-xs" onclick="saveUserInfo()">保存</a>
                    </footer>
                </section>
            </form>
        </div>
    </div>
</section>