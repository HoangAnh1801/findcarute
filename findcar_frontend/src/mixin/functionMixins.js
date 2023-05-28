export const functionMixins ={
    methods: {
        formatDateTime(date1) {
            const dateString = date1;

// Chuyển chuỗi thành đối tượng ngày
            const date = new Date(dateString);

// Lấy thông tin ngày, tháng, năm từ đối tượng ngày
            const day = date.getDate();
            const month = date.getMonth() + 1;
            const year = date.getFullYear();

// Định dạng lại thành chuỗi ngày tháng
            const formattedDate = `${year}-${month}-${day}`;
            return formattedDate
        },
    }
}