<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>评论信息</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
		<#--引入上传文件css-->
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script>
			function updateState(id){
				var load = layer.load();
				$.post('${basePath}/songCommentCtrl/saveAjaxCommentsDetail.shtml',{commentsId:id,isPublic:$("#commentsId").val()},function(result){
					layer.close(load);
					if(result && result.code != 200){
						return layer.msg(result.message,so.default),!1;
					}
					layer.msg('修改成功。');
					setTimeout(function(){
						$('#formId').submit();
					},1000);
				},'json');
			}
		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
	
		
		<@_top.top 5/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.comments 1/>
				<div class="col-md-10">
					<h2>查看评论</h2>
					<hr>
					<table class="table table-bordered">
						<tr>
							<th>评论类型</th>
							<td><#if comments.topicType == '0'>  
										        用户评论
								<#elseif comments.topicType == '1'>  
										        歌曲评论
								<#elseif comments.topicType == '2'>  
										       歌单评论
								</#if></td>
						</tr>
						<tr>
							<th>发起评论人</th>
							<td>${comments.fromUser.nickname?default('-')}</td>
						</tr>
						<tr>
							<th>回复评论人</th>
							<td>${(comments.toUser.nickname)!'-'}</td>
						</tr>
						<tr>
							<th>点赞量</th>
							<td>${(comments.fantasticNum)!'-'}</td>
						</tr>
						<tr>
							<th>评论时间</th>
							<td>${(comments.createTime?string("yyyy-MM-dd hh-mm"))!'-'}</td>
						</tr>
						<tr>
							<th>是否可见</th>
							<td>
							   <select class="form-control" style="width: 100px;" id = "commentsId" name = "topicType" onchange="updateState('${comments.commentsId?default('-')}')">
							      <option value = "1" <#if comments.isPublic?? && comments.isPublic == true>selected</#if>>可见</option>
							      <option value = "0" <#if comments.isPublic?? && comments.isPublic == false>selected</#if>>不可见</option>
							   </select>
							</td>
						</tr>
						<tw
							<th>评论内容</th>
							<td>${(comments.content)!'-'}</td>
						</tr>
					</table>
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