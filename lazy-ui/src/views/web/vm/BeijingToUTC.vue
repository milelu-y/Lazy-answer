<template>
  <div style="max-width: 1200px;margin: 0 auto;padding: 16px;">
    <div><h2>北斗时与北京时的转换</h2></div>
    <div style="margin-top: 10px">
      <a-steps :current="current">
        <a-step v-for="item in steps" :key="item.title" :title="item.title" />
      </a-steps>
    </div>
    <div class="card" style="margin-top: 10px">
      <p>ps:请先将北斗时转换成北京时间进行输入</p>
      <div style="text-align: center">
        <a>北斗时转换成北京时间></a>
        <!--        <a>儒略日转格里高利时></a>-->
        <div style="padding-top: 25px">
          <a-form :form="form">
            <a-row :gutter="24">
              <a-col :span="12">
                <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol"
                             label="北斗时">
                  <a-input placeholder="请输入北斗时"/>
                </a-form-item>
              </a-col>
              <a-col :span="12">
                <a-form-item :label-col="formItemLayout.labelCol" :wrapper-col="formItemLayout.wrapperCol" label="北京时间">
                  <a-input placeholder="请输入北京时间"/>
                  <!--                  <a-date-picker show-time placeholder="选择时间" @change="onChange" @ok="onOk" />-->
                </a-form-item>
              </a-col>
            </a-row>
            <a-row>
              <a-col :span="24" :style="{ textAlign: 'center' }">
                <a-button type="primary" html-type="submit">
                  提交
                </a-button>
              </a-col>
            </a-row>
          </a-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const formItemLayout = {
  labelCol: {span: 8},
  wrapperCol: {span: 8},
};
const formTailLayout = {
  labelCol: {span: 4},
  wrapperCol: {span: 8, offset: 4},
};
export default {
  name: "BeijingTo北京时间",
  data() {
    return {
      form: {},
      formItemLayout,
      formTailLayout,
      current: 0,
      steps: [
        {
          title: '输入',
        },
        {
          title: '提交',
        },
        {
          title: '结果',
        },
      ],
    }
  },
  methods: {
    onChange(value, dateString) {
      console.log('Selected Time: ', value);
      console.log('Formatted Selected Time: ', dateString);
    },
    onOk(value) {
      console.log('onOk: ', value);
    },
    next() {
      this.current++;
    },
    prev() {
      this.current--;
    },
  },
}
</script>

<style scoped>

.card {
  min-height: 250px;
  border-radius: 2px;
  padding: 16px;
  margin-bottom: 16px;
  box-shadow: 0 1px 6px rgb(0 0 0 / 12%), 0 1px 4px rgb(0 0 0 / 12%);
}
.steps-content {
  margin-top: 16px;
  border: 1px dashed #e9e9e9;
  border-radius: 6px;
  background-color: #fafafa;
  min-height: 200px;
  text-align: center;
  padding-top: 80px;
}

.steps-action {
  margin-top: 24px;
}
</style>
