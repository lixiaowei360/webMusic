<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>轮播图展示</title>
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
			function deleteById(ids){
				var index = layer.confirm(" 确定这"+ ids.length +"轮播吗？",function(){
					var load = layer.load();
					$.post('${basePath}/carouselContentCtrl/deleteCarouselContent.shtml',{ids:ids.join(',')},function(result){
						layer.close(load);
						if(result && result.code != 200){
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
		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
		<#--引入头部-->
		<@_top.top 6/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<#--引入左侧菜单-->
				<@_left.carousel 1/>
				<div class="col-md-10">
					<h2>轮播列表</h2>
					<hr>
					<form method="post"  action="" id="formId" class="form-inline">
						<div clss="well">
						  <label for="name">轮播类型:</label>
					      <div class="form-group">
					       	  <select class="form-control" style="width: 100px;" id = "homeId" name = "type">
					      	      <option value = "" >请选择</option>
							      <option value = "0">歌单</option>
							      <option value = "1">歌曲</option>
							      <option value = "2">新闻</option>
							   </select>
					      </div>
					      
					      <label for="name">轮播名称:</label>
					     <div class="form-group">
					        <input type="text" class="form-control"  
					        			name="name" id="findContent" placeholder="输入路轮播名称">
					      </div>
					     <div class="form-group">
				         		<button type="submit" class="btn btn-primary">查询</button>
				         		<a class="btn btn-success" href= "${basePath}/carouselContentCtrl/addCarouselContent.shtml">增加轮播</a>
				        </div>
					<hr>
					<table class="table table-bordered">
						<tr>
							<th>轮播类型</th>
							<th>轮播名字</th>
							<th>创建时间</th>
							<th>点击量</th>
							<th>排序</th>
							<th>状态</th>
							<th >操作</th>
						</tr>
						<#if page?exists && page.list?size gt 0 >
							<#list page.list as it>
								<tr>
									<td>
										<#if it.type == 0>  
										    歌单
										<#elseif it.type == 1>  
										    歌曲
										<#else>  
										   新闻
										</#if>  
									</td>
									<td>${it.name?default('-')}</td>
									<td>${(it.createTime?string("yyyy-MM-dd"))!'-'}</td>
									<td>${(it.clicks)!'-'}</td>
									<td>${(it.orderId)!'-'}</td>
									<td>${(it.status)!'-'}</td>
									<td>
											<i class="glyphicon glyphicon-edit"></i><a href="${basePath}/carouselContentCtrl/addCarouselContent.shtml?carouselId=${it.carouselId}">修改</a>
											<i class="glyphicon glyphicon-remove"></i><a href="javascript:deleteById(['${it.carouselId}']);">删除</a>
									</td>
								</tr>
							</#list>
						<#else>
							<tr>
								<td class="text-center danger" colspan="7">没有找到任何轮播内容</td>
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