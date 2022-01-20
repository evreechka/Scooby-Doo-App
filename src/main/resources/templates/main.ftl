<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if profile??>
        <h3>Crimes</h3>
        <form action="/contention/add">
            <button type="submit" class="btn btn-dark">
                Add Crime
            </button>
        </form>
        <table class="table">
            <#list crimes as crime>
                <tr>
                    <td>Crime ${crime.id}</td>
                    <td>
                        <div class="alert alert-<#if crime.getCrimeStatus().name() == 'ACTIVE'>warning<#elseif crime.getCrimeStatus().name() == 'POSTPON'>info<#else>success</#if>"  role="alert">
                            ${crime.getCrimeStatus()}
                        </div>
                    </td>
                    <td>
                        <form action="/crime/${crime.getId()?c}" method="get">
                            <button type="submit" class="btn btn-secondary">
                                More information
                            </button>
                        </form>
                    </td>

                </tr>
            </#list>
        </table>
    </#if>
</@c.page>