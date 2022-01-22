<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Add victim in Crime visit ${crimeVisitId}</h3>
        <form method="post" action="/victim/${crimeVisitId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Indication:</label>
                <textarea name="indication"
                          class="form-control ${(indicationError??)?string('is-invalid', '')}"/>
                <#if indicationError??>
                    <div class="invalid-feedback">
                        ${indicationError}
                    </div>
                </#if>
            </div>
            <div class="form-check">
                <#list users as user>
                    <div>
                        <input class="form-check-input" type="radio" value="${user.getId()?c}"
                               id="${user.getId()}" name="userId">
                        <label class="form-check-label" for="${user.getId()}" style="word-break: break-all">
                            ${user.getName()} ${user.getSurname()}
                        </label>
                    </div>
                </#list>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>