
<template>
    <div class="home-main sm-root">
        <Row :gutter="10" class="margin-top-10">
            <Col span="10">
                <Card>
                    <Row type="flex" class="user-infor">
                        <Col span="24">
                            <h1>Dear：{{username}}, Welcome~</h1>
                        </Col>
                    </Row>
                    <div class="line-gray"></div>
                </Card>
            </col>
        </Row>
    </div>
</template>

<script>

import Cookies from 'js-cookie';

export default {
    name: 'home',
    data () {
        return {
            department: Cookies.get('department'),
            company: Cookies.get('company'),
            username: Cookies.get('name')
        };
    }
};
</script>
<style>
.line-gray{
    height: 0;
    border-bottom: 1px solid #dcdcdc;
}
</style>
