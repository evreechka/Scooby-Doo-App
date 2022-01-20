<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <form method="post" action="/crime_visit/${crimeId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Destruction:</label>
                <div class="col-sm-6">
                    <input type="text" name="severityDestruction"
                           class="form-control ${(severityDestructionError??)?string('is-invalid', '')}"/>
                    <#if severityDestructionError??>
                        <div class="invalid-feedback">
                            ${severityDestructionError}
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