<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>添加歌曲</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />


		<#--引入上传文件css-->
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/fileinput.min.css" rel="stylesheet"/>
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/css/laydate/laydate.js"></script>

        <script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>


        <link href="http://twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
        <!-- wysiwyg -->
       <script src="${basePath}/js/frontDesk/wysiwyg/jquery.hotkeys.js"></script>
        <script src="${basePath}/js/frontDesk/wysiwyg/bootstrap-wysiwyg.js"></script>


		<#--引入上传文件js-->
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput.min.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/fileinput_locale_zh.js"></script>
		
		<#--引入树样式-->
		<link href="${basePath}/css/tree/zTreeStyle.css" rel="stylesheet"/>
		<script  src="${basePath}/js/tree/jquery.ztree.core.min.js"></script>
		<script  src="${basePath}/js/tree/jquery.ztree.excheck.min.js"></script>
		<script  src="${basePath}/js/tree/jquery.ztree.exedit.min.js"></script>
		<script>
		//弹出层的全局变量
		var index = null;
		 var setting = {
 		 async: {
              enable: true,
              url: '${basePath}/songClassify/selectSongClassifyTreeData.shtml',//异步加载时的请求地址
              autoParam: ["id"],//提交参数
              type: 'get',
              dataType: 'json'
          },
          checkable: true,
          showIcon: true,
          showLine: true,
          data: {
              simpleData: {
                  enable: true
              }
          },
          expandSpeed: "",
          callback: {
              onClick: zTreeOnClick
          }
 		};
	      function zTreeOnClick(event, treeId, treeNode, clickFlag) {
	      
	      		if(treeNode.isParent === false){//子节点
	      			$("#songClassifyNameId").val(treeNode.name);
	      			$("#songClassifyNameIdId").val(treeNode.id);
	      			  layer.close(index);
	      		}
	          var treeValue = treeNode.id + "," + treeNode.name;
	          
	      };
		
			function popTreeData(){
				 //设置位置
				   index = layer.open({
                    type: 1,
                    area:['350px', '400px'],
                    title:'请选择',
                    skin: 'layui-layer-demo', //加上边框
                    content: $('#layerWindow')
                });
                
                layer.style(index, {
                    top: '20%'
                });
                
               $.ajax({
	              url: '${basePath}/songClassify/selectSongClassifyTreeData.shtml?id=0',
	              type: 'get',
	              dataType: 'json',
	              success: function (data) {
	                  $.fn.zTree.init($("#treeType"), setting,data );
	                var treeObj = $.fn.zTree.getZTreeObj("treeType");
	                //父节点不能选择
	                var nodes = treeObj.transformToArray(treeObj.getNodes());
	                for (var i=0, l=nodes.length; i < l; i++) {
                      if (nodes[i].isParent){
                          treeObj.setChkDisabled(nodes[i], true);
                      }
                    }
	                  
	              }
	          });
			}
　　        
    	//初始化fileinput控件（第一次初始化）
   		 function initFileSongInput(ctrlName, fileSize,fileType) {
   		 
   		 	//var initialPreviewhtml = '<audio id="myaudio" src="${songList.songUrl!}" controls="controls" loop="false">';
   		 	var initialPreviewhtml = "<a></a>";
            var control = $('#' + ctrlName);
            control.fileinput({
            	previewFileType:'any',
            	showCaption: false,
                language: 'zh', //设置语言
                dropZoneEnabled:true,
                allowedFileExtensions: fileType, //接收的文件后缀
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
                initialPreview:['<audio class="kv-preview-data file-preview-audio" controls="" style="width:100%;height:30px;">'+
					'<source src="${songList.songUrl!}" type="audio/mp3">'+
					'<div class="file-preview-other">'+
					'<span class="file-other-icon"><i class="glyphicon glyphicon-king"></i></span>'+
					'</div></audio>'],
        		initialPreviewConfig: [
        			{caption: "${songList.songSinger!}", size: 329892, width: "120px", url: "${songList.songUrl!}", key: 1}
        		],
                uploadAsync:false
            });
        }
        
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
		        	'<img src = "${songList.songPictureUrl!}"'+
		        	'class="file-preview-image kv-preview-data"'+
		        	'title="1.png"'+
		        	'alt="1.png"'+
		        	'style="width:auto;height:auto;max-width:100%;max-height:100%;"'+
		        	'</div>'],
		        initialPreviewConfig: [
        			{caption: "${songList.songSinger!}", size: 329892, width: "120px", url: "${songList.songUrl!}", key: 1}
        		],
        		uploadAsync:false
      		  });
        }
        $(document).ready(function(){
			initFileSongInput("fileSong",20,['mp3']);
			initFilePictureInput("filePicture",20,['jpg', 'png','bmp','jpeg']);
            $('#editor').html();

            laydate.render({
                elem: '#relaseDataId',
                type:'datetime'
            });

        });
        $(function(){
            function initToolbarBootstrapBindings() {
                var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
                            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
                            'Times New Roman', 'Verdana'],
                        fontTarget = $('[title=Font]').siblings('.dropdown-menu');
                $.each(fonts, function (idx, fontName) {
                    fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
                });
                $('a[title]').tooltip({container:'body'});
                $('.dropdown-menu input').click(function() {return false;})
                        .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
                        .keydown('esc', function () {this.value='';$(this).change();});

                $('[data-role=magic-overlay]').each(function () {
                    var overlay = $(this), target = $(overlay.data('target'));
                    overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
                });
                $('#voiceBtn').hide();
                if ("onwebkitspeechchange"  in document.createElement("input")) {
                    var editorOffset = $('#editor').offset();
                    $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
                } else {
                    $('#voiceBtn').hide();
                }
            };
            function showErrorAlert (reason, detail) {
                var msg='';
                if (reason==='unsupported-file-type') {
                    msg = "Unsupported format " +detail;
                } else {
                    console.log("error uploading file", reason, detail);
                }
                $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">×</button>'+
                        '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
            };
            initToolbarBootstrapBindings();
            $('#editor').wysiwyg({ fileUploadError: showErrorAlert} );
        });
        
			function UpdateRichTextData() {
				$("#songLyricId").val($("#editor").html());
				return true;
            }


		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
		<@_top.top 4/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.songManager 2/>
				<div class="col-md-10">
					<h2>添加歌曲</h2>
					<hr>
					<form method="post" onsubmit="return UpdateRichTextData()" class="form-horizontal" action="${basePath}/songOperation/saveSongList.shtml"  role="form" enctype="multipart/form-data">
							<input type="text"  name = "songListId" value = "${songList.songListId!}" style="display:none"/>
							<input type="text"  name = "songUrl" value = "${songList.songUrl!}" style="display:none"/>
							<input type="text"  name = "songPictureUrl" value = "${songList.songPictureUrl!}" style="display:none"/>
						  <div class="form-group">
    							<label for="name">歌曲名称</label>
    							<input required type="text" class="form-control" name = "songName" placeholder="请输入歌曲名称" value = "${songList.songName!}">
  						  </div>
  						  <div class="form-group">
    							<label for="name">歌曲分类</label>
    							<input required id = "songClassifyNameId" type="text" class="form-control"  onClick="popTreeData()" value = "${songList.songClassify.songClassifyName!}" placeholder="单击选择分类">
    							<input id = "songClassifyNameIdId" name="songClassifyId" style="display:none" value = "${songList.songClassifyId!}" />
  						  </div>
  						 <div class="form-group" style="display: none">
							    <label for="name">歌词</label>
							    <textarea required class="form-control" rows="5" name = "songLyric" placeholder="请输入歌词" id = "songLyricId">
							    	${songList.songLyric!}
							    </textarea>
						  </div>
                        <div class="form-group">
                            <label >歌词</label>
                            <!--这里加上是为了让提示信息显示 不然会被遮挡-->
                            <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
                                <div class="btn-group">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i class="icon-font"></i><b class="caret"></b></a>
                                    <ul class="dropdown-menu"> </ul>
                                </div>
                                <div class="btn-group">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="icon-text-height"></i> <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
                                        <li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
                                        <li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a> <!--加粗-->
                                    <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a><!-- 斜体-->
                                    <a class="btn" data-edit="strikethrough" title="Strikethrough"><i class="icon-strikethrough"></i></a><!-- 删除线-->
                                    <a class="btn" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a><!-- 下划线-->
                                </div>
                                <div class="btn-group">
                                    <a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i class="icon-list-ul"></i></a><!-- 加点-->
                                    <a class="btn" data-edit="insertorderedlist" title="Number list"><i class="icon-list-ol"></i></a><!-- 数字排序-->
                                    <a class="btn" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a><!-- 减少缩进-->
                                    <a class="btn" data-edit="indent" title="Indent (Tab)"><i class="icon-indent-right"></i></a><!--增加缩进-->
                                </div>
                                <div class="btn-group">
                                    <a class="btn" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a><!--左对齐-->
                                    <a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a><!--居中-->
                                    <a class="btn" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a><!--右对齐-->
                                    <a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a><!--垂直对齐-->
                                </div>
                                <div class="btn-group">
                                    <a class="btn dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="icon-link"></i></a><!-- 链接-->
                                    <div class="dropdown-menu input-append">
                                        <input class="span2" placeholder="URL" type="text" data-edit="createLink"/>
                                        <button class="btn" type="button">Add</button>
                                    </div>
                                    <a class="btn" data-edit="unlink" title="Remove Hyperlink"><i class="icon-cut"></i></a>
                                </div>
                                <div class="btn-group">
                                    <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="icon-picture"></i></a>
                                    <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
                                </div>
                                <div class="btn-group">
                                    <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a><!--撤销-->
                                    <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a><!--恢复-->
                                </div>
                            </div>

                            <div id="editor" name = "songLyric" class="form-control" style="overflow:scroll;height:150px;max-height:150px">
							${songList.songLyric!}
                            </div>
						</div>

                        <div class="form-group">
                            <label for="name">发布日期</label>
                            <input required id = "relaseDataId"type="text" class="form-control" name = "releaseTime" value = "${(songList.releaseTime?string("yyyy-MM-dd HH:mm:ss"))!''}">
                        </div>

						  <div class="form-group">
    							<label for="name">歌手</label>
    							<input required type="text" class="form-control" name = "songSinger" placeholder="请输入歌手" value = "${songList.songSinger!}">
  						  </div>
  						   <div class="form-group">
  						   	  <label for="name">是否对外公开</label>
						  	  <select class="form-control" id = "homeId" name = "songOpenPublic">
							      <option value = "0" <#if (songList.songOpenPublic)?? && songList.songOpenPublic == "0">selected = "selected"</#if>>不公开</option>
							      <option value = "1" <#if (songList.songOpenPublic)?? &&songList.songOpenPublic == "1">selected = "selected"</#if>>公开</option>
							   </select>
						   </div>
						   <div class="form-group">
						   		<label for="name">是否允许下载</label>
							   <select class="form-control" id = "homeId" name = "songOpenDownload">
							      <option value = "0" <#if (songList.songOpenDownload)?? && songList.songOpenDownload == "0">selected = "selected"</#if>>不允许</option>
							      <option value = "1" <#if (songList.songOpenDownload)?? && songList.songOpenDownload == "1">selected = "selected"</#if>>允许</option>
							   </select>
						   </div>
  						  <div class="form-group">
  						   			<label for="name">上传歌曲封面</label>
        						<div class="file-loading">
           						   <input ${((songList.songListId)??)?string('','required')} id="filePicture"  type="file" name = "picture" >
           						 </div>
           				   </div>
  						   <div class="form-group">
  						   			<label for="name">上传歌曲</label>
        						<div class="file-loading">
           						   <input  ${((songList.songListId)??)?string('','required')}  id="fileSong"  type="file" name = "song" >
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
</html>