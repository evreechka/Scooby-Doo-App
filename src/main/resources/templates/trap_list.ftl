<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <h1>Traps</h1>
    <#list traps as trap>
        <h3 style="word-break: break-all">${trap.getName()}</h3>
        <div>
            Usefulness: ${trap.getUsefulness() * 100 / 10}%
        </div>
        <b>Items</b>
        <ul class="list-group">
            <#list trap.getTrapItems() as item>
                <li class="list-group-item">
                    <b>Name</b>:${item.getItem().getName()};
                    <b>Count</b>: ${item.getNecessaryCount()};
                </li>
            </#list>
        </ul>
        <hr>
    </#list>
</@c.page>