<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <form method="post" action="/crime/${contentionId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Description:</label>
                <textarea type="text" name="description"
                          class="form-control ${(descriptionError??)?string('is-invalid', '')}"></textarea>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">fee:</label>
                <div class="col-sm-6">
                    <input type="text" name="fee"
                           class="form-control ${(feeError??)?string('is-invalid', '')}"/>
                    <#if feeError??>
                        <div class="invalid-feedback">
                            ${feeError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Sheriff Id:</label>
                <div class="col-sm-6">
                    <input type="text" name="sheriffId"
                           class="form-control ${(idError??)?string('is-invalid', '')}"/>
                    <#if idError??>
                        <div class="invalid-feedback">
                            ${idError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-check">
                <#list investigators as inv>
                    <div>
                        <input class="form-check-input" type="checkbox" value="${inv.getInvestigatorId()?c}"
                               id="${inv.getInvestigatorId()}" name="invIds">
                        <label class="form-check-label" for="${inv.getInvestigatorId()}" style="word-break: break-all">
                            ${inv.getCharacter().getName()} ${inv.getCharacter().getSurname()}
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