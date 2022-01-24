<#import "parts/common.ftl" as cl>

<@cl.page>
    <h3>Create Crime Scene</h3>
    <div><b>Crime Scene Data</b></div>
    <form method="post" action="/crime_scene/add">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name:</label>
            <div class="col-sm-6">
                <input type="text" name="name"
                       class="form-control ${(nameError??)?string('is-invalid', '')}"/>
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01">Place Type</label>
            </div>
            <select class="custom-select" id="inputGroupSelect01" name="place">
                <option value="PUBLIC" selected>PUBLIC</option>
                <option value="PRIVATE_HOUSE">PRIVATE HOUSE</option>
                <option value="COMPANY_BUILDING">COMPANY BUILDING</option>
                <option value="STREET">STREET</option>
            </select>
        </div>
        <#if placeError??>
            <div class="alert alert-danger" role="alert">
                ${placeError}
            </div>
        </#if>
        <div><b>Address Data</b></div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">City:</label>
            <div class="col-sm-6">
                <input type="text" name="city"
                       class="form-control ${(cityError??)?string('is-invalid', '')}"/>
                <#if cityError??>
                    <div class="invalid-feedback">
                        ${cityError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Avenue:</label>
            <div class="col-sm-6">
                <input type="text" name="avenue"
                       class="form-control ${(avenueError??)?string('is-invalid', '')}"/>
                <#if avenueError??>
                    <div class="invalid-feedback">
                        ${avenueError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">House Number:</label>
            <div class="col-sm-6">
                <input type="text" name="houseNumber"
                       class="form-control ${(houseNumberError??)?string('is-invalid', '')}"/>
                <#if houseNumberError??>
                    <div class="invalid-feedback">
                        ${houseNumberError}
                    </div>
                </#if>
            </div>
        </div>
        <button id="but" class="btn btn-primary" type="submit">
            Create Crime Scene
        </button>
        <script type="application/javascript">
            document.getElementById("but").addEventListener('click', function (event) {
                let eve = new Event("storage", {bubbles: true})
                localStorage.setItem("event", "go");
                window.dispatchEvent(eve);
            });
        </script>
    </form>
</@cl.page>
