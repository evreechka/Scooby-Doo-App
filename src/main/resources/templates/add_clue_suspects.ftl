<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Add Suspects to Clue ${clueId}</h3>
        <form method="post" action="/clue/${criminalCase.getId()?c}/${clueId?c}/add_suspects">
            <div class="form-check">
                <#list criminalCase.getAllSuspect() as suspect>
                    <div>
                        <input class="form-check-input" type="checkbox" value="${suspect.getId()?c}"
                               id="${suspect.getId()}" name="suspectIds">
                        <label class="form-check-label" for="${suspect.getId()}" style="word-break: break-all">
                            ${suspect.getCharacter().getName()} ${suspect.getCharacter().getSurname()}
                        </label>
                    </div>
                </#list>
            </div>
            <button class="btn btn-dark" type="submit">
                Add
            </button>
        </form>
    </#if>
</@c.page>