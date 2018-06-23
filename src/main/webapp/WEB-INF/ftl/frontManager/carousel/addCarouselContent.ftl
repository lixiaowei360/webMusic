<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>轮播管理</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
		
		<#--引入上传文件css-->
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/fileinput.min.css" rel="stylesheet"/>
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<#--引入上传文件js-->
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput.min.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput_locale_zh.js"></script>
		
		<#--引入树样式-->
		<link href="${basePath}/css/tree/zTreeStyle.css" rel="stylesheet"/>
		<script  src="${basePath}/js/tree/jquery.ztree.core.min.js"></script>
		<script  src="${basePath}/js/tree/jquery.ztree.excheck.min.js"></script>
		<script  src="${basePath}/js/tree/jquery.ztree.exedit.min.js"></script>
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
		        	'<img src = "${carouselContent.url!}"'+
		        	'class="file-preview-image kv-preview-data"'+
		        	'title="1.png"'+
		        	'alt="1.png"'+
		        	'style="width:auto;height:auto;max-width:100%;max-height:100%;"'+
		        	'</div>'],
		        initialPreviewConfig: [
        			{caption: "${carouselContent.name!}", size: 329892, width: "120px", url: "${carouselContent.url!}", key: 1}
        		],
        		uploadAsync:false
      		  });
        }
        
        $(document).ready(function(){
			initFilePictureInput("filePicture",20,['jpg', 'png','bmp','jpeg']);
		});
		
		
		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
	
		
		<@_top.top 6/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.carousel 1/>
				<div class="col-md-10">
					<h2>轮播管理</h2>
					<hr>
					<form method="post" class="form-horizontal" action="${basePath}/carouselContentCtrl/saveCarouselContent.shtml"  role="form" enctype="multipart/form-data">
							<input type="text"  name = "carouselId" value = "${carouselContent.carouselId!}" style="display:none"/>
						  
						  <div class="form-group">
    							<label for="name">轮播类型</label>
    						  <select class="form-control" style="width: 100px;" id = "homeId" name = "type">
					      	      <option value = "">请选择</option>
							      <option value = "0"  <#if (carouselContent.type)??&&carouselContent.type == 0>selected</#if> >歌单</option>
							      <option value = "1"  <#if (carouselContent.type)??&&carouselContent.type == 1>selected</#if> >歌曲</option>
							      <option value = "2"  <#if (carouselContent.type)??&&carouselContent.type == 2>selected</#if> >新闻</option>
							   </select>
  						  </div>
  						  
						  <div class="form-group">
    							<label for="name">轮播名称</label>
    							<input ${((carouselContent.carouselId)??)?string('','required')} type="text" class="form-control" name = "name" placeholder="请输入歌曲名称" value = "${carouselContent.name!}">
  						  </div>
  						  <div class="form-group">
    							<label for="name">点击量</label>
    							<input type="text" class="form-control" name = "clicks" placeholder="请输入歌曲名称" value = "${carouselContent.clicks!}">
  						  </div>
  						  <div class="form-group">
    							<label for="name">排序</label>
    							<input ${((carouselContent.carouselId)??)?string('','required')} type="text" class="form-control" name = "orderId" placeholder="请输入歌曲名称" value = "${carouselContent.orderId!}">
  						  </div>
  						  <div class="form-group">
    							<label for="name">状态</label>
    						   <select class="form-control" style="width: 100px;" id = "homeId" name = "status">
					      	      <option value = "">请选择</option>
							      <option value = "0"  <#if (carouselContent.status)??&&carouselContent.status == 0>selected</#if> >关闭</option>
							      <option value = "1"  <#if (carouselContent.status)??&&carouselContent.status == 1>selected</#if> >打开</option>
							   </select>
  						  </div>
  						  <div class="form-group">
  						   			<label for="name">上传歌单封面</label>
        						<div class="file-loading">
           						   <input ${((carouselContent.carouselId)??)?string('','required')}id="filePicture"  type="file" name = "picture" >
           						 </div>
           				   </div>
           				   <input  type="submit" class="btn btn-success save" value="保存"/>  
					</form>
				</div>
			</div><#--/row-->
		</div>
	</body>
	<div id = "layerWindow">
		<div class="zTreeDemoBackground left">
				<ul id="treeType" class="ztree"></ul>
		</div>
	</div>
	<div id = "selectSongSheetUserIdDiv">
	</div>
</html>