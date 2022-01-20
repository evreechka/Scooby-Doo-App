<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h3>Inventory</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Serial number</th>
                <th scope="col">Name</th>
                <th scope="col">Type</th>
                <th scope="col">Accessibility</th>
                <th scope="col">Deposit date</th>
            </tr>
            </thead>
            <tbody>
            <#list items as item>
                <tr>
                    <td>${item.getId()}</td>
                    <td style="word-break: break-all">${item.getItem().getName()}</td>
                    <td>${item.getItem().getType().name()}</td>
                    <td><#if item.isAvailable()>Yes<#else>No</#if></td>
                    <td>${item.getDepositDate()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@c.page>