<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
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
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Character id:</label>
                <div class="col-sm-6">
                    <input type="text" name="id"
                           class="form-control ${(idError??)?string('is-invalid', '')}"/>
                    <#if idError??>
                        <div class="invalid-feedback">
                            ${idError}
                        </div>
                    </#if>
                </div>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>