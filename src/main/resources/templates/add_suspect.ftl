<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <form method="post" action="/suspect/${crimeVisitId}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Motive:</label>
                    <textarea name="motive"
                           class="form-control ${(motiveError??)?string('is-invalid', '')}"/>
                    <#if motiveError??>
                        <div class="invalid-feedback">
                            ${motiveError}
                        </div>
                    </#if>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Involvement:</label>
                <div class="col-sm-6">
                    <input type="text" name="involvement"
                           class="form-control ${(involvementError??)?string('is-invalid', '')}"/>
                    <#if involvementError??>
                        <div class="invalid-feedback">
                            ${involvementError}
                        </div>
                    </#if>
                </div>
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