function sendAttributes(item_list, id) {
    const nameTrap = document.getElementsByClassName('name').item(0).value;
    const item_count = new Map()
    for (let item of item_list) {
        item_count.set(item, document.getElementById(item).value);
    }
    fetch('/order/' + id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name: nameTrap, itemCount: item_count})
    });

}