<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>资料修改 —个人中心</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
		<#--引入上传文件css-->
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/fileinput.min.css" rel="stylesheet"/>
		<#--引入上传文件js-->
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput.min.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput_locale_zh.js"></script>
		<script >
		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
		
		<@_top.top 1/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.user 1/>
				<div class="col-md-10">
					<h2>资料修改</h2>
					<hr>
					<form  enctype="multipart/form-data" action="${basePath}/user/updateSelf.shtml" method="post">
						  <input type="hidden" value="${user.id}" name="id"/>
						  <div class="form-group">
						    <label for="nickname">昵称</label>
						    <input type="text" class="form-control" autocomplete="off" id="nickname" maxlength="8" name="nickname" value="${user.nickname?default('未设置')}" placeholder="请输入昵称">
						  </div>
						  <div class="form-group">
						    <label for="email">Email</label>
						    <input type="text" class="form-control "  autocomplete="off" id="email" maxlength="64" name="email" value="${user.email?default('未设置')}" placeholder="请输入帐号">
						  </div>
						  <div class="form-group">
					   		<label for="name">上传头像</label>
    						<div class="file-loading">
       						   <input id="filePicture" type="file" name = "picture" >
       						</div>
           				   </div>
						  <div class="form-group">
							  <button type="submit" class="btn btn-success">确定修改</button>
						  </div>
						</form>
					
				</div>
				 <#--地图
				<@_html.tool_map/>
				-->
			</div><#--/row-->
		</div>
		<#-- 页脚
		<@_footer.footer 0/>
		-->
		<script src="${cdn}/js/common/jquery/jquery.form-2.82.js?${_v}"></script>
		<script>
		
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
		        	'<img src = "${songSheet.songPicture!}"'+
		        	'class="file-preview-image kv-preview-data"'+
		        	'title="1.png"'+
		        	'alt="1.png"'+
		        	'style="width:auto;height:auto;max-width:100%;max-height:100%;"'+
		        	'</div>'],
		        initialPreviewConfig: [
        			{caption: "${user.id!}", size: 329892, width: "120px", url: "${user.headPicUrl!}", key: 1}
        		],
        		uploadAsync:false
      		  });
        }
        
		$(document).ready(function(){
			initFilePictureInput("filePicture",20,['jpg', 'png','bmp','jpeg']);
		});
		</script>
			
	</body>
</html>