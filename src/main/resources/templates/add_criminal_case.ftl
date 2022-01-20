<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <form method="post" action="/criminal_case/${crimeId?c}/${monsterId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Severity:</label>
                <div class="col-sm-6">
                    <input type="text" name="severity"
                           class="form-control ${(severityError??)?string('is-invalid', '')}"/>
                    <#if severityError??>
                        <div class="invalid-feedback">
                            ${severityError}
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