<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>角色列表 - 权限管理</title>
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
		<@_top.top 4/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<#--引入左侧菜单-->
				<@_left.songManager 3/>
				<div class="col-md-10">
					<h2>歌单列表</h2>
					<hr>
					<form method="post"  action="" id="formId" class="form-inline">
						<div clss="well">
						  <label for="name">歌单描述:</label>
					      <div class="form-group">
					        <input type="text" class="form-control"  value="${findContent?default('')}" 
					        			name="songIntroduce" id="findContent" placeholder="输入歌曲名称">
					      </div>
					      
					      <label for="name">创建人:</label>
					     <div class="form-group">
					        <input type="text" class="form-control"  value="${findContent?default('')}" 
					        			name="user.nickname" id="findContent" placeholder="输入创建人">
					      </div>
					     <div class="form-group">
				         		<button type="submit" class="btn btn-primary">查询</button>
				         		<a class="btn btn-success" href= "${basePath}/songSheetCtrl/addSongSheet.shtml" >增加歌单</a>
				        </div>
					<hr>
					<table class="table table-bordered">
						<tr>
							<th>歌单名称</th>
							<th>歌单创建人</th>
							<th>点击量</th>
                            <th>分类</th>
                            <th>我喜欢收藏</th>
							<th>创建时间</th>
							<th>修改时间</th>
							<th >操作歌单</th>
							<th >操作歌曲</th>
						</tr>
						<#if page?exists && page.list?size gt 0 >
							<#list page.list as it>
								<tr>
									<td>${it.songSheetName?default('-')}</td>
									<td>${(it.user.nickname)!'-'}</td>
									<td>${(it.songSheetClick)!'-'}</td>
									<td>
										<#if it.iCollect == true>
											是
										<#else>
											否
										</#if>
									</td>
                                    <td>${(it.songClassify.songClassifyName)!'-'}</td>
									<td>${(it.createTime?string("yyyy-MM-dd"))!'-'}</td>
									<td>${(it.createTime?string("yyyy-MM-dd"))!'-'}</td>
									
									<td>
											<i class="glyphicon glyphicon-edit"></i><a href="${basePath}/songSheetCtrl/addSongSheet.shtml?songSheetId=${it.songSheetId}">修改</a>
											<i class="glyphicon glyphicon-remove"></i><a href="javascript:deleteById(['${it.songSheetId}']);">删除</a>
									</td>
									<td>
											<i class="glyphicon glyphicon-edit"></i><a href="${basePath}/songSheetCtrl/lookSongLists.shtml?songSheetId=${it.songSheetId}&type=edit">查看歌单歌曲</a>
											<i class="glyphicon glyphicon-edit"></i><a href="${basePath}/songSheetCtrl/lookSongLists.shtml?songSheetId=${it.songSheetId}&type=add">增加歌单歌曲</a>
									</td>
								</tr>
							</#list>
						<#else>
							<tr>
								<td class="text-center danger" colspan="6">没有找到任何歌曲</td>
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