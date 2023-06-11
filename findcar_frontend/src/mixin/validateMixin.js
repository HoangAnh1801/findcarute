import {defineRule} from 'vee-validate';

defineRule('required', values => {
    var value = values.toString();

    if (!value || !value.length) {
        return 'Trường này không được để trống!'
    } else if ((!value.trim() || !value.trim().length)) {
        return 'Trường này không được để trống!'
    }
    return true;
});
defineRule('require', value => {
    if (!value || !value.length) {
        return 'Trường này không được để trống!'
    } else if ((!value.trim() || !value.trim().length)) {
        return 'Trường này không được để trống!'
    }
    return true;
});
defineRule('selected', value => {
    console.log('value', value)
     // Check if it's empty.
    if (Object.keys(value.ten).length == 0) {
        return 'Trường này không được để trống!'
    }
    if ((!value && value === undefined) || value === null || value.length === 0) {
        return 'Trường này không được để trống!'
    }

    return true;
});
defineRule('confirmed', (value, [target], ctx) => {
    if (value !== ctx.form[target]) {
        return 'Mật khẩu không khớp!'
    }
    return true
});

defineRule('oldPassword', value => {
    if (!value || !value.length) {
        return 'Trường này không được để trống!'
    }

    return 'Mật khẩu cũ không chính xác!'
});

defineRule('minLength', (value, [limit]) => {
    if (!value || !value.length) {
        return true;
    }
    if (value.length < limit) {
        return 'message.validateForm.minLength', {'min':limit}}
    return true;
});


// defineRule('password', (value) => {
//     var regex = /^(?=.*[0-9])(?=.*[A-Za-z]).{6,50}$/;
//
//     if (!value || !value.length) {
//         return 'Trường này không được để trống!'
//     }
//
//     if (regex.test(value)) {
//         return true
//     } else {
//         return 'Mật khẩu phải có ít nhất 5 ký tự, bao gồm: chữ và số'
//     }
//     return true
// });