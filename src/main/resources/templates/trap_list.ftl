<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <h1 xmlns="http://www.w3.org/1999/html">Traps</h1>
    <#list object.getPage(page_counter) as trap>
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
    <form>
        <button type="submit" class="btn btn-dark" onsubmit="${object.increment()}">Next</button>
    </form>
</@c.page>