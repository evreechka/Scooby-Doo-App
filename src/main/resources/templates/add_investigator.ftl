<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Add Investigator</h3>
        <form method="post" action="/investigator/add">
            <div><b>Choose user:</b></div>
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
            <div><b>Choose feature:</b></div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Features</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="feature">
                    <option value="INTELLECT" selected>INTELLECT</option>
                    <option value="INTUITION">INTUITION</option>
                    <option value="SPEED">SPEED</option>
                    <option value="FEARLESS">FEARLESS</option>
                </select>
            </div>
            <button class="btn btn-dark" type="submit">
                Add
            </button>
        </form>
    </#if>
</@c.page>