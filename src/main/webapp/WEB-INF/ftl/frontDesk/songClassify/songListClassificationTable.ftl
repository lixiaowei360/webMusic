<#list songClassifyList as songClassify>
 <#if songClassify.hierarchy == 1>
     <#if songClassify_index != 0>
            </div>
         </div>
     </#if>
    <div class="row">
        <div class="col-md-2 ">
                <a href="#" style="margin-right: 10px" ><span >${songClassify.songClassifyName}</span></a>
        </div>
     <div class="col-md-10">
 </#if>
    <#if songClassify.hierarchy != 1>
        <a href="#" style="margin-right: 10px" onclick="getSongSheetClassifi('${songClassify.songClassifyId}','${songClassify.songClassifyName}')"> <span>${songClassify.songClassifyName}</span></a>
    </#if>
</#list>

