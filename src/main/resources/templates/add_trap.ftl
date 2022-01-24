<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#assign trap_list = map?keys>
<@c.page>
    <#if profile??>
        <h3>Add Trap for Criminal case ${criminalCaseId}</h3>
        <form method="post" action="/order/${criminalCaseId?c}">
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
            <div><b>Choose items and count:</b></div>
            <div class="input-group">
                <#list map as item, count>
                    <span class="input-group-text" id="${item}">${item}</span>
                    <input name="${item}" type="text" class="form-control ${(countError??)?string('is-invalid', '')}"
                           aria-describedby="${item}" value="${count}"/>
                    <#if countError??>
                        <div class="invalid-feedback">
                            ${countError}
                        </div>
                    </#if>
                </#list>
            </div>
            <button class="btn btn-dark" type="button" onclick="sendAttributes(${criminalCaseId})">
                Add
            </button>
        </form>
    </#if>
</@c.page>