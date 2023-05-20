import {defineRule} from 'vee-validate';

defineRule('required', value => {
    if (!value || !value.length) {
        return 'Trường này không được để trống!'
    } else if ((!value.trim() || !value.trim().length)) {
        return 'Trường này không được để trống!'
    }
    return true;
});
defineRule('selected', value => {
    if ((!value && value === undefined) || value === null) {
        // return i18n.global.t('message.validateForm.selectObject')
    }

    return true;
});