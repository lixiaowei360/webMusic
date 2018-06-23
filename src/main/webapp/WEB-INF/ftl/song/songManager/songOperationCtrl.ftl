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
        <script  src="${basePath}/js/common/layer/layer.js"></script>
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
					$.post('${basePath}/songOperation/deleteSongList.shtml',{ids:ids.join(',')},function(result){
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

			function message(){
           /*     layer.msg('保存成功');*/
			}
		</script>
	</head>
	<body data-target="#one" data-spy="scroll"  onload="message()">
		<#--引入头部-->
		<@_top.top 4/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<#--引入左侧菜单-->
				<@_left.songManager 2/>
				<div class="col-md-10">
					<h2>歌曲列表</h2>
					<hr>
					<form method="post"  action="" id="formId" class="form-inline">
						<div clss="well">
						  <label for="name">歌曲名字:</label>
					      <div class="form-group">
					        <input type="text" class="form-control"  value="${findContent?default('')}" 
					        			name="songName" id="findContent" placeholder="输入歌曲名称">
					      </div>
					      <label for="name">歌曲公开状态:</label>
					      <div class="form-group">
					      	   <select class="form-control" style="width: 100px;" id = "homeId" name = "songOpenPublic">
					      	      <option value = "" >请选择</option>
							      <option value = "0" >不公开</option>
							      <option value = "1">公开</option>
							   </select>
					      </div>
					      
					       <label for="name">歌手:</label>
					      <div class="form-group">
 							 <input type="text" class="form-control"  value="${findContent?default('')}" 
					        			name="songSinger" id="findContent" placeholder="输入歌手">
					      </div>
					     </div>
					    <div class="form-group">
				         		<button type="submit" class="btn btn-primary">查询</button>
				         		<a class="btn btn-success" href= "${basePath}/songOperation/addSongList.shtml" >增加歌曲</a>
				         		<button type="button" id="deleteAll" class="btn  btn-danger">删除</button>
				        </div>
				        
					<hr>
					<table class="table table-bordered">
						<tr>
							<th><input type="checkbox" id="checkAll"/></th>
							<th>歌曲名字</th>
							<th>歌曲分类</th>
							<th>歌手</th>
							<th>公开搜索</th>
							<th>公开下载</th>
							<th>操作</th>
						</tr>
						<#if page?exists && page.list?size gt 0 >
							<#list page.list as it>
								<tr>
									<td><input value="${it.songListId}" check='box' type="checkbox" /></td>
									<td>${it.songName?default('-')}</td>
									<td>${it.songClassify.songClassifyName?default('-')}</td>
									<td>${it.songSinger?default('-')}</td>
									<td>
										<#if it.songOpenPublic == '0'>
											否
										<#else> 
											是
										</#if>  
									</td>
									<td>
										<#if it.songOpenDownload == '0'>
											否
										<#else> 
											是
										</#if>  
									</td>
									<td>
											<i class="glyphicon glyphicon-edit"></i><a href="${basePath}/songOperation/addSongList.shtml?songListId=${it.songListId}">修改</a>
											<i class="glyphicon glyphicon-remove"></i><a href="javascript:deleteById(['${it.songListId}']);">删除</a>
											
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