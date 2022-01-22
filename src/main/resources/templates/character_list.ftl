<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h3>Characters</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Full name</th>
                <th scope="col">Age</th>
                <th scope="col">Sex</th>
                <th scope="col">Role in system</th>
            </tr>
            </thead>
            <tbody>
            <#list characters as char>
                <tr>
                    <td>${char.getId()}</td>
                    <td style="word-break: break-all">${char.getName()} ${char.getSurname()}</td>
                    <td>${char.getAge()}</td>
                    <td>${char.getSex().name()}</td>
                    <td><#if char.getProfile()??>${char.getProfile().getRole()}</#if></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#if>
</@c.page>