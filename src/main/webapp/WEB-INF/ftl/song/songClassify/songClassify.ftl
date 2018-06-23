<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>权限分配 - 权限管理</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<#--引入树样式-->
		<link href="${basePath}/css/tree/zTreeStyle.css" rel="stylesheet"/>
		<link   rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		

	
		<script  src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script  src="${basePath}/js/shiro.demo.js"></script>
		
		<#--引入树js-->
		<script  src="${basePath}/js/tree/jquery.ztree.core.min.js"></script>
		<script  src="${basePath}/js/tree/jquery.ztree.excheck.min.js"></script>
		<script  src="${basePath}/js/tree/jquery.ztree.exedit.min.js"></script>
		<script >
		
		   var setting = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				editNameSelectAll: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onRemove: onRemove,
				onRename: onRename,
				onClick:editSogClassify
			}
		};
		var zNodes = ${treeData};
		var log, className = "dark";
		
		
	   $(function () { $('#editClassify').on('hide.bs.modal', function () {
  			$("#name").val("");
			$("#sortId").val("");
			$("#homeId").val("");
			$("#primary").val("");
			$("#pId").val("");
  			})
		});
   
		function saveSogClassify(){
		
			$('#editClassify').modal();
				$.post("${basePath}/songClassify/updateSongClassify.shtml",
				{
					songClassifyId:$("#primary").val(),
					classifySort:$("#sortId").val(),
					classifyHome:$("#homeId").val(),
					pid:$("#pId").val(),
					songClassifyName:$("#name").val()
				}
				,function(data){
					 document.location.reload();//当前页面 
				},'json');
				
		}
		
		function editSogClassify(event, treeId, treeNode){
			$("#name").val(treeNode.name);
			$("#sortId").val(treeNode.classifySort);
			$("#homeId").val(treeNode.classifyHome);
			$("#primary").val(treeNode.id);
			$("#pId").val(treeNode.pId);
			$('#editClassify').modal();
		}
		
		
		
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function beforeEditName(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			setTimeout(function() {
					setTimeout(function() {
						zTree.editName(treeNode);
					}, 0);
			}, 0);
			return false;
		}
		function beforeRemove(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		}
		function onRemove(e, treeId, treeNode) {
		
				$.post("${basePath}/songClassify/deleteSongClassify.shtml",
				{
					songClassifyId:treeNode.id,
				}
				,function(data){
				},'json');
			showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		}
		function beforeRename(treeId, treeNode, newName, isCancel) {
			className = (className === "dark" ? "":"dark");
			showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
			if (newName.length == 0) {
				setTimeout(function() {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.cancelEditName();
					alert("节点名称不能为空.");
				}, 0);
				return false;
			}
			return true;
		}
		
		//修改名字成功,调用这个方法
		function onRename(e, treeId, treeNode, isCancel) {
		
			$.post("${basePath}/songClassify/updateSongClassify.shtml",
				{
					songClassifyId:treeNode.id,
					songClassifyName:treeNode.name
				}
				,function(data){
				},'json');
			showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		}
		function showRemoveBtn(treeId, treeNode) {
			return !treeNode.hasOwnProperty("children");
		}
		function showRenameBtn(treeId, treeNode) {
			return true;
		}
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='增加节点' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				//ajax异步请求数据
				$.post("${basePath}/songClassify/addSongClassify.shtml",
				{
					pid:treeNode.id,
					songClassifyName:"悬浮鼠标修改节点"
				}
				,function(data){
					zTree.addNodes(treeNode, {id:data.id, pId:treeNode.id, name:"悬浮鼠标修改节点" + (newCount++)});
					var node = zTree.getNodeByParam("id", newID, null); //根据新的id找到新添加的节点  
					zTree.selectNode(node); //让新添加的节点处于选中状态 
				},'json');
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting,zNodes);
			$("#selectAll").bind("click", selectAll);
			
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
			treeObj.expandAll(true); 
		});
		</script>
	</head>
	<body data-target="#one" data-spy="scroll">
		<@_top.top 4/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<#--引入左侧菜单-->
				<@_left.songManager 1/>
				<div class="col-md-10">
					<h2>歌曲分类</h2>
					<hr>
					<form method="post" action="" id="formId" class="form-inline">
						<div clss="well">
					      <div class="form-group">
					        <input type="text" class="form-control" style="width: 300px;" value="${songClassifyName?default('')}" 
					        			name="songClassifyName" id="findContent" placeholder="输入歌曲分类">
					      </div>
					     <span class=""> <#--pull-right -->
				         	<button type="submit" class="btn btn-primary">查询</button>
				         </span>    
				        </div>
					<hr>
						<div id="treeDemo" class="ztree">
						</div>
					<#if page?exists>
						<div class="pagination pull-right">
							${page.pageHtml}
						</div>
					</#if>
					</form>
				</div>
			</div>
		</div>
		
		
		
		<#--添加弹框-->
				<div class="modal fade" id="editClassify" tabindex="-1" role="dialog" aria-labelledby="addroleLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="addroleLabel">修改歌单分类</h4>
				      </div>
				      <div class="modal-body">
				        <form id="boxRoleForm">
				          <div class="form-group">
				            <label for="recipient-name" class="control-label" >歌单名字:</label>
				            <input type="text" class="form-control" name="name" id="name" placeholder="歌单名字"/>
				          </div>
				          <div class="form-group">
				            <label for="recipient-name" class="control-label">歌单排序:</label>
				            <input type="text" class="form-control" id="sortId" name="type"  >
				          </div>
				          	 
				          	  <input id = "primary" style="display:none"/>
				          	   <input id = "pId" style="display:none"/>
				          	   <label for="recipient-name" class="control-label">首页状态:</label>
				              <select class="form-control" id = "homeId">
							      <option value = "0">不显示</option>
							      <option value = "1">显示</option>
							   </select>
				        </form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				        <button type="button" onclick="saveSogClassify();" class="btn btn-primary">保存</button>
				      </div>
				    </div>
				  </div>
				</div>
		<#--/添加弹框-->
	</body>
</html>