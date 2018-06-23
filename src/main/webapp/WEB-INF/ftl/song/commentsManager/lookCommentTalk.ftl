<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>留言管理</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script  src="${basePath}/js/shiro.demo.js"></script>
		<script >
			so.init(function(){
				//初始化全选。
				so.checkBoxInit('#checkAll','[check=box]');
				//全选
				so.id('deleteAll').on('click',function(){
					var checkeds = $('[check=box]:checked');
					if(!checkeds.length){
						return layer.msg('请选择要删除的选项。',so.default),!0;
					}
					var array = [];
					checkeds.each(function(){
						array.push(this.value);
					});
					return deleteById(array);
				});
			});
			function deleteById(ids){
				var index = layer.confirm(" 确定这"+ ids.length +"歌曲吗？",function(){
					var load = layer.load();
					$.post('${basePath}/songSheetCtrl/deleteSongSheet.shtml',{ids:ids.join(',')},function(result){
						layer.close(load);
						if(result && result.status != 200){
							return layer.msg(result.message,so.default),!0;
						}else{
							layer.msg(result.resultMsg);
							setTimeout(function(){
								$('#formId').submit();
							},1000);
						}
					},'json');
					layer.close(index);
				});
			}
			
			function addRole(){
				var name = $('#name').val(),
					type = $('#type').val();
				if($.trim(name) == ''){
					return layer.msg('角色名称不能为空。',so.default),!1;
				}
				if(!/^[a-z0-9A-Z]{6}$/.test(type)){
					return layer.msg('角色类型为6数字字母。',so.default),!1;
				}
				<#--loding-->
				var load = layer.load();
				$.post('${basePath}/role/addRole.shtml',{name:name,type:type},function(result){
					layer.close(load);
					if(result && result.status != 200){
						return layer.msg(result.message,so.default),!1;
					}
					layer.msg('添加成功。');
					setTimeout(function(){
						$('#formId').submit();
					},1000);
				},'json');
			}
		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
		<#--引入头部-->
		<@_top.top 5/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<#--引入左侧菜单-->
				<@_left.comments 1/>
				<div class="col-md-10">
					<h2>歌单列表</h2>
					<hr>
					<form method="post"  action="" id="formId" class="form-inline">
						<div clss="well">
						  <label for="name">评论类型:</label>
					      <div class="form-group">
							  <select class="form-control" style="width: 100px;" id = "homeId" name = "topicType">
					      	      <option value = "" >请选择</option>
							      <option value = "0">回复用户</option>
							      <option value = "1">歌曲评论</option>
							      <option value = "2">歌单评论</option>
							   </select>
					      </div>
					      
					      <label for="name">内容:</label>
					     <div class="form-group">
					        <input type="text" class="form-control"  
					        			name="content" id="findContent" placeholder="输入评论内容">
					      </div>
					     <div class="form-group">
				         		<button type="submit" class="btn btn-primary">查询</button>
				        </div>
					<hr>
					<table class="table table-bordered" style="table-layout:fixed">
						<tr>
							<th class="center" style='width:10%;'>评论类型</th>
							<th class="center" style='width:10%;'>发起评论人</th>
							<th class="center" style='width:10%;'>回复评论人</th>
							<th class="center" style='width:10%;'>点赞量</th>
							<th class="center" style='width:10%;'>创建时间</th>
							<th class="center" style='width:10%;'>评论内容</th>
							<th class="center" style='width:10%;'>对外是否可见</th>
							<th class="center" style='width:10%;'>删除</th>
						</tr>
						<#if page?exists && page.list?size gt 0 >
							<#list page.list as it>
								<tr>
									<td>
										<#if it.topicType == '0'>  
										        用户评论
										<#elseif it.topicType == '1'>  
										        歌曲评论
										<#elseif it.topicType == '2'>  
										       歌单评论
										</#if>  										
									</td>
									<td>${it.fromUser.nickname?default('-')}</td>
									<td>${(it.toUser.nickname)!'-'}</td>
									<td>${(it.fantasticNum)!'-'}</td>
									<td>${(it.createTime?string("yyyy-MM-dd HH:mm"))!'-'}</td>
									<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
										${(it.content)!'-'}
									</td>
									<td>
										<#if (it.isPublic)?? >
										 	<#if it.isPublic == true>
										 	对外公开
										 	<#elseif it.isPublic == false>
										 	不对外开放
										 	</#if>
										 <#else>
										 	空数据
										</#if>
									</td>
									<td>
										<i class="glyphicon glyphicon-info-sign"></i><a href="${basePath}/songCommentCtrl/lookCommentsDetail.shtml?commentsId=${it.commentsId}">查看评论详情</a>
										<i class="glyphicon glyphicon-remove"></i><a href="javascript:deleteById(['${it.commentsId}']);">删除</a>
									</td>
								</tr>
							</#list>
						<#else>
							<tr>
								<td class="text-center danger" colspan="8">没有找到任何评论</td>
							</tr>
						</#if>
					</table>
					<#if page?exists>
						<div class="pagination pull-right">
							${page.pageHtml}
						</div>
					</#if>
					</form>
				</div>
			</div><#--/row-->
		</div>
			
	</body>
</html>