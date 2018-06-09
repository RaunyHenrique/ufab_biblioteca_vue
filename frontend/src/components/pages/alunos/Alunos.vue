<template>

  <div>

    <!--Title-->
    <b-row>

      <b-col>

        <h1 id="title-page" class="text-left">Alunos</h1>
        <b-button @click.stop="showModal('Cadastrar', null, null, $event.target)" id="btn-cadastrar" variant="primary" style="display: inline-block;">Cadastrar aluno</b-button>

      </b-col>

    </b-row>

    <hr>

    <!-- User Interface controls -->
    <b-row id="ui-table">

      <b-col md="6" class="my-1" id="per-page-select">
        <b-form-group horizontal label="Por página" class="mb-0">
          <b-form-select :options="pageOptions" v-model="perPage" />
        </b-form-group>
      </b-col>

      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Filtrar" class="mb-0">
          <b-input-group>
            <b-form-input v-model="filter" placeholder="Buscar..." />
            <b-input-group-append>
              <b-btn variant="primary" :disabled="!filter" @click="filter = ''">Apagar</b-btn>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>

    </b-row>

    <!-- Main table element -->
    <b-table show-empty
             stacked="md"
             :items="items"
             :fields="fields"
             :current-page="currentPage"
             :per-page="perPage"
             :filter="filter"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             :sort-direction="sortDirection"
             @filtered="onFiltered"
             id="table-listar"
    >

      <template slot="curso" slot-scope="row">{{row.value.nome}} - {{row.value.tipo}}</template>

      <template slot="actions" slot-scope="row">
        <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
        <b-button variant="warning" size="sm" @click.stop="showModal('Editar', row.item, row.index, $event.target)" class="mr-1">
          Editar
        </b-button>

        <b-button variant="danger" size="sm" @click.stop="showDeleteModal('Deletar', row.item, row.index, $event.target)">
          Deletar
        </b-button>

      </template>

    </b-table>

    <!-- Pagination -->
    <b-row>
      <b-col md="6" class="my-1">
        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" class="my-0" />
      </b-col>
    </b-row>

    <!-- modal-add-edit -->
    <b-modal id="modal-add-edit" @hide="resetModal" :title="modalInfo.title" :cancel-title="'Cancelar'" :ok-title="'Salvar'" @ok="handleOk">

      <form @submit.stop.prevent="handleSubmit">

        <b-form-input style="display: none;" id="id"
                      name="id"
                      type="number"
                      v-model="form.id">
        </b-form-input>

        <b-form-group label="Nome"
                      label-for="nome">
          <b-form-input id="nome"
                        name="nome"
                        type="text"
                        v-model="form.nome"
                        required
                        placeholder="Digite seu nome">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Rg"
                      label-for="rg">
          <b-form-input id="rg"
                        name="rg"
                        type="number"
                        v-model="form.rg"
                        required
                        placeholder="Digite seu rg">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Cpf"
                      label-for="cpf">
          <b-form-input id="cpf"
                        name="cpf"
                        type="number"
                        v-model="form.cpf"
                        required
                        placeholder="Digite seu cpf">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Naturalidade"
                      label-for="naturalidade">
          <b-form-input id="naturalidade"
                        name="naturalidade"
                        type="text"
                        v-model="form.naturalidade"
                        required
                        placeholder="Digite sua naturalidade">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Nome da Mãe"
                      label-for="nomeMae">
          <b-form-input id="nomeMae"
                        name="nomeMae"
                        type="text"
                        v-model="form.nomeMae"
                        required
                        placeholder="Digite o nome da sua mãe">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Endereço"
                      label-for="endereco">
          <b-form-input id="endereco"
                        name="endereco"
                        type="text"
                        v-model="form.endereco"
                        required
                        placeholder="Digite seu endereço">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Telefone"
                      label-for="telefone">
          <b-form-input id="telefone"
                        name="telefone"
                        type="number"
                        v-model="form.telefone"
                        required
                        placeholder="Digite seu telefone">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Ano de Ingresso"
                      label-for="anoIngresso">
          <b-form-input id="anoIngresso"
                        name="anoIngresso"
                        type="date"
                        v-model="form.anoIngresso"
                        required
                        placeholder="Digite seu ano de ingresso">
          </b-form-input>
        </b-form-group>

        <b-form-group label="Periodo de Ingresso"
                      label-for="periodoIngresso">
          <b-form-select id="periodoIngresso"
                         name="periodoIngresso"
                         :options="periodosDeIngresso"
                         required
                         v-model="form.periodoIngresso">
            <template slot="first">
              <option :value="null">Selecione...</option>
            </template>
          </b-form-select>
        </b-form-group>

        <b-form-group label="Nível de graduação"
                      label-for="nivel">
          <b-form-select id="nivel"
                         name="nivel"
                         :options="niveisDeGraduacao"
                         required
                         v-model="form.nivel"
                          @change="setCursosPorNivel">
            <template slot="first">
              <option :value="null">Selecione...</option>
            </template>
          </b-form-select>
        </b-form-group>

        <b-form-group label="Curso"
                      label-for="curso">
          <b-form-select id="curso"
                         name="curso"
                         :options="filtedCursos"
                         required
                         v-model="form.curso">
            <template slot="first">
              <option :value="null">Selecione...</option>
            </template>
          </b-form-select>
        </b-form-group>

      </form>

    </b-modal>

    <!-- modal-delete -->
    <b-modal id="modal-delete" @hide="resetModal" :title="modalInfo.title" :cancel-title="'Cancelar'" @ok="handleDelete">

      <h4>Deseja realmente deletar este item?</h4>

    </b-modal>

  </div>

</template>

<script>

  export default {
    name: "Alunos",
    data () {
      return {
        items: [],
        fields: [
          { key: 'matricula', label: 'Matricula', sortable: true},
          { key: 'nome', label: 'Nome', sortable: true, 'class': 'text-center' },
          { key: 'curso', label: 'Curso/Graduação', sortable: true },
          { key: 'anoIngresso', label: 'Ano de ingresso', sortable: true },
          { key: 'telefone', label: 'Telefone'},
          { key: 'actions', label: 'Ações' }
        ],
        currentPage: 1,
        perPage: 5,
        totalRows: 0,
        pageOptions: [ 5, 10, 15 ],
        sortBy: null,
        sortDesc: false,
        sortDirection: 'asc',
        filter: null,
        modalInfo: { title: '', content: '' },
        form: {},
        cursos: [],
        filtedCursos: [],
        periodosDeIngresso: {'1': 'Primeiro semestre', '2': 'Segundo semestre'},
        niveisDeGraduacao: {},
        idToDelete: null,
        indexToDelete: null,
      }
    },
    created() {
      this.getAllAlunos()
      this.getAllNiveis()
      this.getAllCursos()
    },
    methods: {
      getAllAlunos () {

        this.$http.get('/alunos')
          .then(data => {

            console.log(data.data[0]);

            this.items = data.data
            this.totalRows = this.items.length

            this.setFormFields(data.data[0])

          })
          .catch((error) => {
            console.log(error)
          })

      },
      getAllNiveis () {

        this.$http.get('/alunos/tipos_de_niveis')
          .then(data => {

            this.niveisDeGraduacao = data.data

          })
          .catch((error) => {
            console.log(error)
          })

      },
      getAllCursos () {

        this.$http.get('/cursos')
          .then(data => {

            var cursos = []
            data.data.forEach(function (obj) {

              cursos.push({text: obj['nome'], value: obj['id'], disabled: false})

            })

            this.cursos = cursos

          })
          .catch((error) => {
            console.log(error)
          })

      },
      setCursosPorNivel (evt) {

        console.log("OPAA " + evt)

        var value = evt

        switch(value){
          case 'GRADUACAO':
            this.listFiltedCursos()
            break
          case 'ESPECIALIZACAO':
            this.listFiltedCursos()
            break
          case 'MESTRADO':
            this.listFiltedCursos()
            break
          case 'DOUTORADO':
            this.listFiltedCursos()
            break
          case 'POSGRADUACAO':
            this.listFiltedCursos()
            break
          default: //default child option is blank
            $("#curso").html('')
            break
        }

      },
      filtrarPorNivel(value) {
        return value['tipo'] == this.form.nivel;
      },
      listFiltedCursos() {

        var cursosFiltrados = this.cursos.filter(this.filtrarPorNivel)

        console.log(cursosFiltrados)

        this.filtedCursos = []
        var cursos = []
        cursosFiltrados.forEach(function (obj) {

          cursos.push({text: obj['text'], value: obj['value'], disabled: false})

        })

        this.filtedCursos = cursos

      },
      setFormFields(data) {

        var keys = {}
        Object.keys(data).forEach(function (key) {
          keys[key] = null
        })

        this.form = keys

      },
      showModal(title, item, index, event) {
        this.modalInfo.title = title
        this.modalInfo.content = item
        this.$root.$emit('bv::show::modal', 'modal-add-edit', event)
      },
      showDeleteModal(title, item, index, event) {
        this.modalInfo.title = title
        this.idToDelete = item['id']
        this.indexToDelete = index
        this.$root.$emit('bv::show::modal', 'modal-delete', event)
      },
      resetModal () {
        this.form = {}
      },
      onFiltered (filteredItems) {
        // Trigger pagination to update the number of buttons/pages due to filtering
        this.totalRows = filteredItems.length
        this.currentPage = 1
      },
      handleDelete () {

        if (this.idToDelete != null) {

          this.$http.delete('/alunos/'+this.idToDelete)
            .then(data => {

              this.items.splice(this.indexToDelete, 1);

              this.idToDelete = null
              this.indexToDelete = null

            })
            .catch((error) => {
              console.log(error)
            })

        }

      },
      handleOk (evt) {
        // Prevent modal from closing
        evt.preventDefault()
        // if (!this.name) {
        //   alert('Please enter your name')
        // } else {
        //   this.handleSubmit()
        // }
      },
      handleSubmit () {
        this.$refs.modal.hide()
      }
    }
  }

</script>

<style scoped>

  #table-listar {
    margin-top: 16px;
  }
  #per-page-select {
    text-align: left !important;
  }
  #per-page-select .custom-select {
    width: 30% !important;
  }
  #ui-table {
    margin-top: 16px;
  }
  #title-page {
    float: left;
  }
  #btn-cadastrar {
    float: right;
    margin-bottom: 12px;
  }
</style>
