function sendAttributes(id) {
    const item_list = []
    const spans = document.getElementsByClassName('input-group-text')
    for (let span of spans) {
        item_list.push(span.id)
    }
    console.log(document.getElementById("name"))
    const nameTrap = document.getElementById("name").value;
    const item_count = new Map()
    for (let item of item_list) {
        item_count.set(item, document.getElementsByName(item).item(0).value);
    }
    fetch('/order/make/' + id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name: nameTrap, itemCount: item_count})
    });

}