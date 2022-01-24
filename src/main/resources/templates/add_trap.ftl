<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#assign trap_list = map?keys>
<@c.page>
    <#if profile??>
        <h3>Add Trap for Criminal case ${criminalCaseId}</h3>
        <form method="post" action="/order/make/${criminalCaseId?c}">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Name:</label>
                <div class="col-sm-6">
                    <input type="text" name="name" id="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
            </div>
            <button class="btn btn-dark" type="button">
                Add
            </button>
        </form>
    </#if>
</@c.page>