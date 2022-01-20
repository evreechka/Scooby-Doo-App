<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h3>Monsters</h3>
        <td>
            <form action="/monster/add" method="get">
                <button type="submit" class="btn btn-dark">
                    Add new monster
                </button>
            </form>
        </td>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Height</th>
                <th scope="col">Weight</th>
                <th scope="col">Type</th>
                <th scope="col">Feature</th>
            </tr>
            </thead>
            <tbody>
            <#list monsters as monster>
                <tr>
                    <td>${monster.getId()}</td>
                    <td style="word-break: break-all">${monster.getName()}</td>
                    <td style="word-break: break-all">
                        <#if monster.getDescription()??>${monster.getDescription()}<#else></#if>
                    </td>
                    <td>${monster.getHeight()}</td>
                    <td>${monster.getWeight()}</td>
                    <td>${monster.getMonsterType().getName()}</td>
                    <td>${monster.getMonsterType().getMonsterFeature().name()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@c.page>