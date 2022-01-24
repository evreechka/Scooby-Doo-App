function sendAttributes(id) {
    const item_list = []
    const spans = document.getElementsByClassName('input-group-text')
    for (let span of spans) {
        item_list.push(span.id)
    }
    const nameTrap = document.getElementsByClassName('name').item(0).value;
    const item_count = new Map()
    for (let item of item_list) {
        item_count.set(item, document.getElementsByName(item).item(0));
    }
    fetch('/order/' + id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name: nameTrap, itemCount: item_count})
    });

}